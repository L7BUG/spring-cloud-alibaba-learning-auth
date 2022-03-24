package com.byaoh.cloud.auth.domain.dataobj;

import com.byaoh.cloud.common.dataobj.BaseDo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Role
 *
 * @author l
 * @date 2022/3/22 下午9:10
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "role", uniqueConstraints = {
	@UniqueConstraint(name = "uc_roledo_name", columnNames = {"name"})
})
public class RoleDo extends BaseDo {
	private static final long serialVersionUID = -6359581678408884603L;

	@Column(name = "name", nullable = false, length = 32)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_menu",
		foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
		inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
		joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id")
	)
	private Set<MenuDo> menus = new LinkedHashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		RoleDo roleDo = (RoleDo) o;
		return getId() != null && Objects.equals(getId(), roleDo.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
