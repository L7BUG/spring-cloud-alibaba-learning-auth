package com.byaoh.cloud.auth.config;

import com.byaoh.cloud.auth.config.etc.SecurityConstants;
import com.byaoh.cloud.auth.exception.JwtAccessDeniedHandler;
import com.byaoh.cloud.auth.exception.JwtAuthenticationEntryPoint;
import com.byaoh.cloud.auth.filter.JwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author l
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/** 认证拦截器 */
	private final JwtAuthorizationFilter jwtAuthorizationFilter;
	/** 用户认证接口 */
	private final UserDetailsService userDetailsService;

	public SecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter, UserDetailsService userDetailsService) {
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// 注入 AuthenticationManager 认证过滤器中要使用
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// 关闭 cors
			.cors().and().csrf().disable()
			// 配置拦截策略
			.authorizeRequests()
			// 放行登陆
			.antMatchers(HttpMethod.POST, SecurityConstants.AUTH_LOGIN_URL).permitAll()
			.antMatchers(SecurityConstants.FILTER_WHITELIST).permitAll()
			// 其他全部不放行
			.anyRequest().authenticated()
			.and()
			.addFilter(jwtAuthorizationFilter)
			// 禁用session
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
			.accessDeniedHandler(new JwtAccessDeniedHandler());
	}

	/**
	 * 身份认证接口
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}
