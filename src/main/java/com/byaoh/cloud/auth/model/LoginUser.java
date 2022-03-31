package com.byaoh.cloud.auth.model;

import com.byaoh.cloud.auth.domain.dataobj.MenuDo;
import com.byaoh.cloud.framework.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证对象
 *
 * @author luliangyu
 * @date 2022-03-03 10:19
 */
@Getter
@Setter
public class LoginUser implements UserDetails {
	private static final long serialVersionUID = 3673555967785529388L;
	private String username;

	private String password;

	private Integer status;

	/**
	 * token
	 */
	private String userToken;

	/**
	 * 菜单
	 */
	private Set<MenuDo> menuSet;

	/**
	 * 权限
	 */
	private Set<String> permissionSet;

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissionSet.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return Objects.equals(StatusEnum.ENABLE.status, status);
	}
}
