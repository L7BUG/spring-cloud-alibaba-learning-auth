package com.byaoh.cloud.auth.domain.dataobj;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * UserDO
 *
 * @author luliangyu
 * @date 2022-03-03 10:28
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserDo {

	@Id
	private Long id;

	@NotBlank(message = "用户名不能为空")
	@Column(name = "username", unique = true, length = 32, nullable = false)
	private String username;

	@NotBlank(message = "密码不能为空")
	@Column(name = "password", nullable = false)
	private String password;

	@NotNull(message = "状态不能为空")
	@Column(name = "status", length = 1, nullable = false)
	private Integer status;

	public Collection<GrantedAuthority> authorities() {
		return List.of();
	}
}
