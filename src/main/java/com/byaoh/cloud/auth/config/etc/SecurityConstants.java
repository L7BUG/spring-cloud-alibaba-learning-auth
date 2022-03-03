package com.byaoh.cloud.auth.config.etc;

/**
 * 认证常量
 *
 * @author l
 * @date 2022/3/1 下午9:11
 */
public interface SecurityConstants {
	/**
	 * Security过滤白名单
	 */
	String[] FILTER_WHITELIST = {
		"/swagger-ui.html",
		"/swagger-ui/*",
		"/swagger-resources/**",
		"/v2/api-docs",
		"/v3/api-docs",
		"/webjars/**"
	};
	/**
	 * jwt发行人
	 */
	String ISS = "l";
	/**
	 * 用于设置map role:key
	 */
	String ROLE_CLAIMS = "role:key";
	/**
	 * 设置密匙
	 */
	String SECRET = "spring-cloud-alibaba-learning-auth";
	/**
	 * 登陆地址
	 */
	String AUTH_LOGIN_URL = "/auth/login";
	/**
	 * jwt 请求头
	 */
	String TOKEN_HEADER = "Authorization";

	/**
	 * jwt 前缀
	 */
	String TOKEN_PREFIX = "Bearer ";
	/**
	 * 过期时间
	 */
	long EXPIRATION = 1000L * 60 * 60 * 6;
}
