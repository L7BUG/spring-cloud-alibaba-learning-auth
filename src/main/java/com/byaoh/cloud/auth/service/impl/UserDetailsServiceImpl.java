package com.byaoh.cloud.auth.service.impl;

import com.byaoh.cloud.auth.domain.dataobj.UserDo;
import com.byaoh.cloud.auth.model.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserDetailsServiceImpl
 *
 * @author luliangyu
 * @date 2022-03-03 10:26
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	/**
	 * 系统内置帐号
	 */
	private static final String SYSTEM_USERNAME;
	/***
	 * 系统内置密码
	 */
	private static final String SYSTEM_PASSWORD;

	static {
		SYSTEM_USERNAME = "bf870eca-8f00-5e52-8777";
		SYSTEM_PASSWORD = "10fd5907-c8bc-59eb-af07";
	}

	private final PasswordEncoder passwordEncoder;

	public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (SYSTEM_USERNAME.equals(username)) {
			return systemUser();
		}
		return null;
	}

	private UserDetails systemUser() {
		UserDo userDo = new UserDo();
		userDo.setStatus(1);
		userDo.setUsername(SYSTEM_USERNAME);
		userDo.setPassword(passwordEncoder.encode(SYSTEM_PASSWORD));
		return new LoginUser(userDo);
	}
}
