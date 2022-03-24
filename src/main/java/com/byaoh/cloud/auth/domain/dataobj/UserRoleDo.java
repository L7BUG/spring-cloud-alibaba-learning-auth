package com.byaoh.cloud.auth.domain.dataobj;

import com.byaoh.cloud.common.dataobj.BaseDo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 用户角色关联信息表 多对多
 *
 * @author l
 * @date 2022/3/22 下午9:39
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "user_role", uniqueConstraints = {
	@UniqueConstraint(name = "uc_userroledo_user_id_role_id", columnNames = {"user_id", "role_id"})
})
public class UserRoleDo extends BaseDo {
	private static final long serialVersionUID = -7042812558147296613L;

	/**
	 * 用户id
	 */
	@Column(name = "user_id", nullable = false)
	@NotNull(message = "用户id不能为空")
	private Long userId;

	/**
	 * 角色id
	 */
	@NotNull(message = "角色id不能为空")
	@Column(name = "role_id", nullable = false)
	private Long roleId;

	@OneToOne(targetEntity = UserDo.class)
	@JoinColumn(name = "user_id", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private UserDo user;

	@OneToOne(targetEntity = RoleDo.class)
	@JoinColumn(name = "role_id", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private RoleDo role;

}
