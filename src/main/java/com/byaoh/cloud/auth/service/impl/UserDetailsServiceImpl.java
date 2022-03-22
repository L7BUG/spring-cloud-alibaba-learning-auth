package com.byaoh.cloud.auth.service.impl;

import com.byaoh.cloud.auth.config.etc.SecurityConstants;
import com.byaoh.cloud.auth.domain.dataobj.UserDo;
import com.byaoh.cloud.auth.model.LoginUser;
import com.byaoh.cloud.common.CommonProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * UserDetailsServiceImpl
 *
 * @author luliangyu
 * @date 2022-03-03 10:26
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final Long systemId;

	public UserDetailsServiceImpl(CommonProperties commonProperties) {
		this.systemId = commonProperties.getSystemUserId();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (SecurityConstants.SYSTEM_USERNAME.equals(username)) {
			return systemUser();
		}
		return null;
	}

	private UserDetails systemUser() {
		UserDo userDo = new UserDo();
		userDo.setId(systemId);
		userDo.setStatus(1);
		userDo.setUsername(SecurityConstants.SYSTEM_USERNAME);
		userDo.setPassword(SecurityConstants.SYSTEM_PASSWORD);
		LoginUser loginUser = new LoginUser();
		loginUser.setUserDo(userDo);
		loginUser.setPermissionSet(Set.of("admin"));
		return loginUser;
	}
}
