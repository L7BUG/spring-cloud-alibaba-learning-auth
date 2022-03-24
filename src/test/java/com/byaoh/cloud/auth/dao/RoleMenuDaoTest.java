package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.TestTemplate;
import com.byaoh.cloud.auth.domain.dataobj.MenuDo;
import com.byaoh.cloud.auth.domain.dataobj.RoleMenuDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class RoleMenuDaoTest extends TestTemplate {
	@Autowired
	private MenuDao menuDao;

	@Autowired
	private RoleMenuDao roleMenuDao;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void saveAllTest() {
		List<MenuDo> all = menuDao.findAll();
		for (MenuDo menuDo : all) {
			RoleMenuDo roleMenuDo = new RoleMenuDo();
			roleMenuDo.setMenuId(menuDo.getId());
			roleMenuDo.setRoleId(1506957128551350272L);
			roleMenuDao.save(roleMenuDo);
		}
	}
}