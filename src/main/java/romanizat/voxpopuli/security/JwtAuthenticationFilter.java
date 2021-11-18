package romanizat.voxpopuli.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import romanizat.voxpopuli.entity.User;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpStatus.NO_CONTENT;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
private final JwtProvider jwtProvider;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
		super(authenticationManager);
		this.jwtProvider = jwtProvider;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			User user = mapper.readValue(request.getInputStream(), User.class);
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null));
		} catch (IOException e) {
			throw new UsernameNotFoundException("User not found");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
		String token = jwtProvider.createToken(authResult.getName(), authResult.getAuthorities());
		response.setHeader(AUTHORIZATION, "Bearer " + token);
		response.setStatus(NO_CONTENT.value());
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.TEXT_PLAIN.toString());
		response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		response.getWriter().write(failed.getMessage());
	}
}