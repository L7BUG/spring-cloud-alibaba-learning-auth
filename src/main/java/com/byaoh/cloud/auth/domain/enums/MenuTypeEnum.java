package com.byaoh.cloud.auth.domain.enums;

import com.byaoh.cloud.framework.base.BaseEnum;

/**
 * 菜单类型枚举
 *
 * @author l
 * @date 2022/3/23 下午5:40
 */
public enum MenuTypeEnum implements BaseEnum<Integer> {
	/**
	 * 目录
	 */
	DIRECTORY(1, "目录"),
	/**
	 * 页面
	 */
	PAGE(2, "页面"),
	/**
	 * 权限
	 */
	PERMISSION(3, "权限"),
	;
	public final int type;
	public final String desc;

	MenuTypeEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public static MenuTypeEnum getByType(int type) {
		for (MenuTypeEnum item : MenuTypeEnum.values()) {
			if (item.type == type) {
				return item;
			}
		}
		return null;
	}

	@Override
	public Integer getValue() {
		return this.type;
	}
}
