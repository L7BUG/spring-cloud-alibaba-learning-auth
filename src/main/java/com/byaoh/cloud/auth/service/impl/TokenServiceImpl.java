package com.byaoh.cloud.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.byaoh.cloud.auth.config.etc.SecurityConstants;
import com.byaoh.cloud.auth.model.LoginUser;
import com.byaoh.cloud.auth.service.TokenService;
import com.byaoh.cloud.auth.utils.JwtTokenUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * TokenServiceImpl
 *
 * @author l
 * @date 2022/3/22 下午6:05
 */
@Service
public class TokenServiceImpl implements TokenService {
	private final RedisTemplate<String, Object> redisTemplate;

	public TokenServiceImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public String createToken(LoginUser loginUser) {
		String token = JwtTokenUtils.createToken(loginUser.getUsername(), loginUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
		loginUser.setUserToken(token);
		// 存入redis
		redisTemplate.opsForValue().set(token, loginUser, SecurityConstants.EXPIRATION, TimeUnit.MILLISECONDS);
		return token;
	}

	@Override
	public LoginUser getLoginUser(HttpServletRequest request) {
		String header = request.getHeader(SecurityConstants.TOKEN_HEADER);
		if (header == null) {
			return null;
		}
		header = header.replaceAll(SecurityConstants.TOKEN_PREFIX, "");
		if (Boolean.FALSE.equals(redisTemplate.hasKey(header))) {
			return null;
		}
		JSONObject o = (JSONObject) redisTemplate.opsForValue().get(header);
		return o.toJavaObject(LoginUser.class);
	}

	@Override
	public void verifyToken(String token) {
		redisTemplate.expire(token, SecurityConstants.EXPIRATION, TimeUnit.MILLISECONDS);
	}
}
