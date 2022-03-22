package com.byaoh.cloud.auth.domain.dataobj;

import com.byaoh.cloud.common.dataobj.BaseDo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@EqualsAndHashCode
@Table(name = "role", uniqueConstraints = {
	@UniqueConstraint(name = "uc_roledo_name", columnNames = {"name"})
})
public class RoleDo extends BaseDo {
	private static final long serialVersionUID = -6359581678408884603L;

	@Column(name = "name", nullable = false, length = 32)
	private String name;
}
