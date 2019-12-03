package com.lucianoortizsilva.workshoppedido.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lucianoortizsilva.workshoppedido.config.security.autenticacao.JWTAuthenticationFilter;
import com.lucianoortizsilva.workshoppedido.config.security.autenticacao.JWTUtil;
import com.lucianoortizsilva.workshoppedido.config.security.autorizacao.JWTAuthorizationFilter;

/**
 * 
 * https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
 * 
 * @author ortiz
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] ACESSO_PUBLIC = { "/h2-console/**" };

	private static final String[] ACESSO_PUBLIC_GET = { "/produtos/**", "/categorias/**", "/clientes/**" };

	@Autowired
	private Environment env;

	@Autowired
	private UserDetailsService userDetailsService; 

	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Para funcionar a page http://localhost:8080/h2-console:
		if (Arrays.asList(this.env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.GET, ACESSO_PUBLIC_GET).permitAll();
		http.authorizeRequests().antMatchers(ACESSO_PUBLIC).permitAll().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), this.jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), this.jwtUtil, this.userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	protected void configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
