package com.byaoh.cloud.auth.domain.dataobj;

import com.byaoh.cloud.auth.domain.dataobj.converter.MenuTypeConverter;
import com.byaoh.cloud.auth.domain.enums.MenuTypeEnum;
import com.byaoh.cloud.common.dataobj.BaseDo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 *
 * @author l
 * @date 2022/3/23 下午6:29
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "menu", uniqueConstraints = {
	@UniqueConstraint(name = "uc_menudo_code", columnNames = {"code"})
})
public class MenuDo extends BaseDo {
	private static final long serialVersionUID = 1410880265808434184L;

	/**
	 * 名称
	 */
	@NotNull(message = "名称不能为空")
	@Column(nullable = false, length = 32)
	private String name;

	/**
	 * 编码
	 */
	@NotNull(message = "编码不能为空")
	@Column(nullable = false, length = 64)
	private String code;

	@Convert(converter = MenuTypeConverter.class)
	@Enumerated
	@Column(nullable = false)
	private MenuTypeEnum type;

	/**
	 * 父级id
	 */
	@NotNull(message = "父级节点id不能为空")
	@Column(name = "father_id", nullable = false)
	private Long fatherId;

	@NotNull(message = "排序字段不能为空")
	@Column(name = "order_num", nullable = false)
	private Byte orderNum = 1;

	/**
	 * 地址
	 */
	private String path;

	/**
	 * 图标
	 */
	private String icon;


	@ToString.Exclude
	@ManyToOne(optional = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "father_id", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private MenuDo father;

	// @JoinColumn(name = "father_id", insertable = false, updatable = false)
	@OneToMany(mappedBy = "father", orphanRemoval = true, fetch = FetchType.EAGER)
	private List<MenuDo> menus = new ArrayList<>();

}
