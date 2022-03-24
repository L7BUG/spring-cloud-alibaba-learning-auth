package com.byaoh.cloud.auth.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoleDaoTest {
	@Autowired
	private RoleDao roleDao;

	@BeforeEach
	void setUp() {
		System.out.println("roleDao.getClass() = " + roleDao.getClass());
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void saveTest() {
	}
}