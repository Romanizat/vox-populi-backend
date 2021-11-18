package romanizat.voxpopuli.security;

import java.util.Collections;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
private final JwtProvider jwtProvider;
	private final UserDetailsService userDetailsService;
	
	public SecurityConfig(JwtProvider jwtProvider, UserDetailsService userDetailsService) {
		this.jwtProvider = jwtProvider;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable()
				.csrf().disable()
				.cors().and()
				.authorizeRequests().anyRequest().authenticated().and()
				.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtProvider))
				.addFilterBefore(new JwtAuthorizationFilter(authorizationManager(), jwtProvider), UsernamePasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
	}

	@Bean
	@Qualifier("authenticationManager")
	@Override
	public AuthenticationManager authenticationManager() {
		return authentication -> {
			String username = authentication.getName().toLowerCase(Locale.ROOT).trim();
			String password = authentication.getCredentials().toString();
			UserDetails user = userDetailsService.loadUserByUsername(username);

			if (!passwordEncoder().matches(password, user.getPassword()))
				throw new BadCredentialsException("auth.invalidCredentials");

			if (!user.isCredentialsNonExpired())
				return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

			return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
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
}