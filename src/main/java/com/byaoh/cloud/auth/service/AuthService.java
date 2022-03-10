package com.byaoh.cloud.auth.service;

import com.byaoh.cloud.auth.model.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证service
 *
 * @author luliangyu
 * @date 2022-03-10 13:59
 */
public interface AuthService {
	/**
	 * 根据request 获取login对象
	 *
	 * @param request 请求
	 * @return 不存在 返回null
	 */
	LoginUser getLoginUser(HttpServletRequest request);

	/**
	 * 给令牌延期 只有 < 1小时过期的才会延期
	 *
	 * @param token 令牌
	 */
	void verifyToken(String token);
}
