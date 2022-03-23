package com.byaoh.cloud.auth.domain.enums;

import com.byaoh.cloud.framework.base.BaseEnum;

/**
 * 菜单类型枚举
 *
 * @author l
 * @date 2022/3/23 下午5:40
 */
public enum MenuTypeEnum implements BaseEnum<Integer> {
	DIRECTORY(0, "目录"),
	PAGE(1, "页面"),
	PERMISSION(2, "权限"),
	;
	public final int type;
	public final String desc;

	MenuTypeEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	@Override
	public Integer getValue() {
		return this.type;
	}
}
