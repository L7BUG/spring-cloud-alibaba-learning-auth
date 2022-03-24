package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.TestTemplate;
import com.byaoh.cloud.auth.domain.dataobj.MenuDo;
import com.byaoh.cloud.auth.domain.enums.MenuTypeEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.UUID;


class MenuDaoTest extends TestTemplate {
	@Autowired
	private MenuDao menuDao;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void saveTest() {
		MenuDo root = new MenuDo();
		String rootCode = UUID.randomUUID().toString().substring(0, 32);
		root.setCode(rootCode);
		root.setName(rootCode);
		root.setFatherId(-1L);
		root.setType(MenuTypeEnum.DIRECTORY);

		MenuDo level1 = new MenuDo();
		String level1Code = UUID.randomUUID().toString().substring(0, 32);
		level1.setCode(level1Code);
		level1.setName(level1Code);
		level1.setType(MenuTypeEnum.PAGE);

		root.setChild(Set.of(level1));
		menuDao.save(root);

		level1.setFatherId(root.getId());
		menuDao.save(level1);

		level1.setName("shit");

		menuDao.save(root);
	}
}