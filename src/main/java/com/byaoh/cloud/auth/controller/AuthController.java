package com.byaoh.cloud.auth.controller;

import com.byaoh.cloud.auth.dto.request.LoginRequest;
import com.byaoh.cloud.auth.service.AuthService;
import com.byaoh.cloud.framework.web.Result;
import com.byaoh.cloud.framework.web.ResultFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 *
 * @author l
 * @date 2022/3/22 下午5:24
 */
@RequestMapping("/auth")
@RestController
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public Result<String> login(@RequestBody LoginRequest loginRequest) {
		String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
		return ResultFactory.success(token);
	}
}
