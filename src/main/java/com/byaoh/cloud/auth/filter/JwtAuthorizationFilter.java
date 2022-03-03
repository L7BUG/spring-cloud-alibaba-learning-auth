package com.byaoh.cloud.auth.filter;

import com.byaoh.cloud.auth.config.etc.SecurityConstants;
import com.byaoh.cloud.auth.utils.JwtTokenUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	private final RedisTemplate<String, String> stringRedisTemplate;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, RedisTemplate<String, String> stringRedisTemplate) {
		super(authenticationManager);
		this.stringRedisTemplate = stringRedisTemplate;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String authorization = request.getHeader(SecurityConstants.TOKEN_HEADER);
//        如果没有 就放行
		if (authorization == null || !authorization.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			SecurityContextHolder.clearContext();
			chain.doFilter(request, response);
			return;
		}
		String token = authorization.replace(SecurityConstants.TOKEN_PREFIX, "");
		String username = JwtTokenUtils.getUsernameByToken(token);
		String redisToken = stringRedisTemplate.opsForValue().get("token:" + username);
//        如果与redis中的不一致 就清空并放行
		if (redisToken == null && !redisToken.equals(token)) {
			SecurityContextHolder.clearContext();
			chain.doFilter(request, response);
			return;
		}
//        向security中添加 token
		UsernamePasswordAuthenticationToken authentication = JwtTokenUtils.getAuthentication(token, username);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}
