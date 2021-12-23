package romanizat.voxpopuli.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import romanizat.voxpopuli.entity.User;
import romanizat.voxpopuli.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Value("${spring.security.disabled:false}")
    private String securityDisabled;

    public SecurityConfig(JwtProvider jwtProvider, UserDetailsService userDetailsService, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (Boolean.parseBoolean(securityDisabled)) {
            http.httpBasic().disable().cors().and()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/**").permitAll().and()
                    .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtProvider));
        } else {
            http.httpBasic().disable()
                    .cors()
                    .and()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    .anyRequest().permitAll()
                    .and()
                    .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtProvider));
        }
    }

    @Bean
    @Qualifier("authenticationManager")
    @Override
    public AuthenticationManager authenticationManager() {
        return authentication -> {
            String username = authentication.getName().toLowerCase(Locale.ROOT).trim();
            String password = authentication.getCredentials().toString();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            User user = userService.findUserByUsername(username);
            if (user.getBanned())
                throw new DisabledException("Account banned");

            if (!passwordEncoder().matches(password, userDetails.getPassword()))
                throw new BadCredentialsException("Incorrect username or password");

            if (!userDetails.isCredentialsNonExpired()) {
                return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            }

            return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
        };
    }

    @Bean
    @Qualifier("authorizationManager")
    public AuthenticationManager authorizationManager() {
        return authentication -> {
            if (authentication == null)
                return null;

            String username = authentication.getName().toLowerCase(Locale.ROOT).trim();
            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (!user.isCredentialsNonExpired())
                return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Authorization", "content-type", "x-xsrf-token", "XSRF-TOKEN"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}