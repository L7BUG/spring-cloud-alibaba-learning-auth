package com.byaoh.cloud.auth.domain.dataobj;

import com.byaoh.cloud.common.dataobj.BaseDo;
import com.byaoh.cloud.framework.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

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
public class UserDo extends BaseDo implements Serializable {

	private static final long serialVersionUID = -933566060022443956L;

	@NotBlank(message = "用户名不能为空")
	@Column(name = "username", unique = true, length = 32, nullable = false)
	private String username;

	@NotBlank(message = "密码不能为空")
	@Column(name = "password", nullable = false)
	private String password;

	@NotNull(message = "状态不能为空")
	@Enumerated
	@Column(name = "status")
	private StatusEnum status;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleDo> roles = new LinkedHashSet<>();

}
