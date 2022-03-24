package com.byaoh.cloud.auth.domain.dataobj;

import com.byaoh.cloud.auth.domain.enums.MenuTypeEnum;
import com.byaoh.cloud.common.dataobj.SnowFlakeDo;
import com.byaoh.cloud.framework.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Objects;
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
public class UserDo extends SnowFlakeDo {

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
		foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
		inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleDo> roles = new LinkedHashSet<>();

	public Set<MenuDo> rootMenu() {
		Set<MenuDo> rootMenus = new LinkedHashSet<>();
		for (RoleDo role : this.roles) {
			role.getMenus().stream().filter(item -> item.getFather() == null).forEach(rootMenus::add);
		}
		return rootMenus;
	}

	public Set<String> permissionsSet() {
		Set<String> permissionsSet = new LinkedHashSet<>();
		for (RoleDo role : this.roles) {
			role.getMenus().stream().filter(item -> Objects.equals(item.getType(), MenuTypeEnum.PERMISSION)).map(MenuDo::getCode).forEach(permissionsSet::add);
		}
		return permissionsSet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		UserDo userDo = (UserDo) o;
		return getId() != null && Objects.equals(getId(), userDo.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
