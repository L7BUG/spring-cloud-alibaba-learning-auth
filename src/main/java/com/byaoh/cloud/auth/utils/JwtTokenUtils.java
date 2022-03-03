package com.byaoh.cloud.auth.utils;

import com.byaoh.cloud.auth.config.etc.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Jwt 工具类
 *
 * @author l
 */
public final class JwtTokenUtils {
	private JwtTokenUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * 用于创建令牌
	 *
	 * @param username 不能为空
	 * @param roles    null 会创建没有角色信息的令牌
	 * @return
	 */
	public static String createToken(String username, List<String> roles) {
		JwtBuilder builder = Jwts.builder();
		if (roles != null) {
			Map<String, Object> map = new HashMap<>();
			map.put(SecurityConstants.ROLE_CLAIMS, String.join(",", roles));
			builder.setClaims(map);
		}
		return builder
			.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
			.setIssuer(SecurityConstants.ISS)
			.setSubject(username)
			.setIssuedAt(new Date())
			.compact();
	}

	/**
	 * 获取该令牌中的用户名
	 *
	 * @param token 不为空
	 * @return 用户名字符串
	 */
	public static String getUsernameByToken(String token) {
		return getTokenBody(token).getSubject();
	}

	/**
	 * 获取全部角色信息
	 *
	 * @param token 不为空
	 * @return 角色信息list
	 */
	public static List<SimpleGrantedAuthority> getUserRolesByToken(String token) {
		String role = (String) getTokenBody(token)
			.get(SecurityConstants.ROLE_CLAIMS);
		return Arrays.stream(role.split(","))
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}

	private static Claims getTokenBody(String token) {
		return Jwts.parser()
			.setSigningKey(SecurityConstants.SECRET)
			.parseClaimsJws(token)
			.getBody();
	}

	public static UsernamePasswordAuthenticationToken getAuthentication(String token, String username) {
		UsernamePasswordAuthenticationToken authenticationToken = null;
		List<SimpleGrantedAuthority> roles = getUserRolesByToken(token);
		authenticationToken =
			new UsernamePasswordAuthenticationToken(username, token, roles);
		return authenticationToken;
	}
}
