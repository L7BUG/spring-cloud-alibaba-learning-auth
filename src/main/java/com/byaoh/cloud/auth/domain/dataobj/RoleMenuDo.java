package com.byaoh.cloud.auth.domain.dataobj;

import com.byaoh.cloud.common.dataobj.BaseDo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * 角色菜单关联
 *
 * @author l
 * @date 2022/3/23 下午7:07
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "role_menu", uniqueConstraints = {
	@UniqueConstraint(name = "uc_rolemenudo_roleid_menuid", columnNames = {"role_id", "menu_id"})
})
public class RoleMenuDo extends BaseDo {
	private static final long serialVersionUID = 807967596821537082L;

	/**
	 * 角色id
	 */
	@Column(name = "role_id", nullable = false)
	@NotNull(message = "角色id不能为空")
	private Long roleId;

	/**
	 * 菜单id
	 */
	@NotNull(message = "菜单id不能为空")
	@Column(name = "menu_id", nullable = false)
	private Long menuId;
}
