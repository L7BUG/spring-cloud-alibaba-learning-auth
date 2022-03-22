package com.byaoh.cloud.auth.exception;

import com.byaoh.cloud.framework.web.Result;
import com.byaoh.cloud.framework.web.ResultCode;
import com.byaoh.cloud.framework.web.ResultFactory;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * AuthenticationEntryPoint 用来解决匿名用户访问需要权限才能访问的资源时的异常
 *
 * @author l
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	/**
	 * 当用户尝试访问需要权限才能的REST资源而不提供Token或者Token错误或者过期时，
	 * 将调用此方法发送401响应以及错误信息
	 */
	@Override
	public void commence(HttpServletRequest request,
						 HttpServletResponse response,
						 AuthenticationException authException) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json; charset=UTF-8");
		Result<Void> result = ResultFactory.build(ResultCode.UNAUTHORIZED);
		JsonMapper.builder().build().writeValue(response.getWriter(), result);
	}
}
