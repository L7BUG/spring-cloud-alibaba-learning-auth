package com.byaoh.cloud.auth.domain.dataobj.converter;

import com.byaoh.cloud.auth.domain.enums.MenuTypeEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 菜单枚举转换类
 *
 * @author l
 * @date 2022/3/23 下午9:42
 */
@Converter
public class MenuTypeConverter implements AttributeConverter<MenuTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(MenuTypeEnum baseEnum) {
		return baseEnum.type;
	}

	@Override
	public MenuTypeEnum convertToEntityAttribute(Integer type) {
		return MenuTypeEnum.getByType(type);
	}
}
