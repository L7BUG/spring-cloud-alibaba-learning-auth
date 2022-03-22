package com.byaoh.cloud.auth.service;

import com.byaoh.cloud.auth.model.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * TokenService
 *
 * @author l
 * @date 2022/3/22 下午6:01
 */
public interface TokenService {
	/**
	 * 根据登陆用户 创建token
	 *
	 * @param loginUser 登陆对象
	 * @return token
	 */
	String createToken(LoginUser loginUser);

	/**
	 * 根据request 获取login对象
	 *
	 * @param request 请求
	 * @return 不存在 返回null
	 */
	LoginUser getLoginUser(HttpServletRequest request);

	/**
	 * 给令牌延期
	 *
	 * @param token 令牌
	 */
	void verifyToken(String token);
}
