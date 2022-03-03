package com.byaoh.cloud.auth.domain.dataobj;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * UserDO
 *
 * @author luliangyu
 * @date 2022-03-03 10:28
 */
@Entity
@Data
@Table(name = "user")
public class UserDo {

	@Id
	@GeneratedValue(generator = "long_id")
	@GenericGenerator(name = "long_id", strategy = "com.byaoh.cloud")
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
		return null;
	}
}
