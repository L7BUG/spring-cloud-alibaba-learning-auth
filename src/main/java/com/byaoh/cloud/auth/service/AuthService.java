package com.byaoh.cloud.auth.service;

/**
 * 认证service
 *
 * @author luliangyu
 * @date 2022-03-10 13:59
 */
public interface AuthService {

	/**
	 * 登陆
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return token
	 */
	String login(String username, String password);

	/**
	 * 校验自己有没有权限
	 *
	 * @param permission 权限名称
	 * @return true 拥有改权限
	 */
	boolean hasPermission(String permission);
}
