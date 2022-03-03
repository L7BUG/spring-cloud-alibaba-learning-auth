package com.byaoh.cloud.auth.config;

import com.byaoh.cloud.auth.config.etc.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author l
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		log.info(encoder.encode("123456"));
		return encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, SecurityConstants.AUTH_LOGIN_URL).permitAll()
			.anyRequest().permitAll()
			.and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
// 			.addFilter(new JwtAuthorizationFilter(authenticationManager(), stringRedisTemplate))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling()
		// .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
		// .accessDeniedHandler(new JwtAccessDeniedHandler())
		;
	}
}
