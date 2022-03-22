package com.byaoh.cloud.auth.service.impl;

import com.byaoh.cloud.auth.model.LoginUser;
import com.byaoh.cloud.auth.service.AuthService;
import com.byaoh.cloud.auth.service.TokenService;
import com.byaoh.cloud.framework.exception.ApiException;
import com.byaoh.cloud.framework.web.ResultCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * AuthServiceImpl
 *
 * @author l
 * @date 2022/3/15 下午10:27
 */
@Service("as")
public class AuthServiceImpl implements AuthService {
	private final TokenService tokenService;

	/**
	 * 安全管理器
	 */
	private final AuthenticationManager authenticationManager;

	public AuthServiceImpl(TokenService tokenService, AuthenticationManager authenticationManager) {
		this.tokenService = tokenService;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public String login(String username, String password) {
		Authentication authenticate;
		try {
			authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (AuthenticationException e) {
			throw new ApiException(ResultCode.UNAUTHORIZED);
		}
		LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
		return tokenService.createToken(loginUser);
	}

	@Override
	public boolean hasPermission(String permission) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		return loginUser.getPermissionSet().contains(permission);
	}
}
