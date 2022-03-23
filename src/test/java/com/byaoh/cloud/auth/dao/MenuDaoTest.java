package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.TestTemplate;
import com.byaoh.cloud.auth.domain.dataobj.MenuDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


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
	void test() {
		List<MenuDo> all = menuDao.findAll();
		for (MenuDo menuDo : all) {
			System.err.println(menuDo);
		}
	}
}