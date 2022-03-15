package com.byaoh.cloud.auth.service.impl;

import com.byaoh.cloud.auth.model.LoginUser;
import com.byaoh.cloud.auth.service.AuthService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * AuthServiceImpl
 *
 * @author l
 * @date 2022/3/15 下午10:27
 */
@Service
public class AuthServiceImpl implements AuthService {
	@Override
	public LoginUser getLoginUser(HttpServletRequest request) {
		return null;
	}

	@Override
	public void verifyToken(String token) {

	}
}
