package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.domain.dataobj.UserRoleDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRoleDaoTest {
	@Autowired
	private UserRoleDao userRoleDao;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void saveTest() {
		UserRoleDo userRoleDo = new UserRoleDo();
		userRoleDo.setUserId(1506956427225956352L);
		userRoleDo.setRoleId(1506957128551350272L);
		userRoleDao.save(userRoleDo);
	}

	@Test
	void findAllTest() {
	}
}