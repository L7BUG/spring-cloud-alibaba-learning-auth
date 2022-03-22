package com.byaoh.cloud.auth.filter;

import com.byaoh.cloud.auth.model.LoginUser;
import com.byaoh.cloud.auth.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器处理所有HTTP请求，并检查是否存在带有正确令牌的Authorization标头。
 * 例如，如果令牌未过期或签名密钥正确。
 *
 * @author l
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	/**
	 * 认证服务
	 */
	private final TokenService tokenService;

	public JwtAuthorizationFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		LoginUser loginUser = tokenService.getLoginUser(request);
		// 请求中包含token 并且 security中获取不到认证信息
		if (loginUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// 延期
			tokenService.verifyToken(loginUser.getUserToken());
			UsernamePasswordAuthenticationToken securityToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
			securityToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(securityToken);
		}
		chain.doFilter(request, response);
	}
}
