package com.byaoh.cloud.auth.model;

import com.byaoh.cloud.auth.domain.dataobj.UserDo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

/**
 * 认证对象
 *
 * @author luliangyu
 * @date 2022-03-03 10:19
 */
public class LoginUser implements UserDetails {
	private static final long serialVersionUID = 3673555967785529388L;
	private final UserDo userDo;

	/**
	 * token
	 */
	private String token;

	public LoginUser(UserDo userDo) {
		this.userDo = userDo;
	}

	public String getToken() {
		return token;
	}

	public LoginUser setToken(String token) {
		this.token = token;
		return this;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userDo.authorities();
	}

	@Override
	public String getPassword() {
		return userDo.getPassword();
	}

	@Override
	public String getUsername() {
		return userDo.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return Objects.equals(userDo.getStatus(), 1);
	}
}
