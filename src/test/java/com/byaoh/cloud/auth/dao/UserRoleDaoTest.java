package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.domain.dataobj.UserRoleDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
		UserRoleDo entity = new UserRoleDo();
		entity.setRoleId(1506290279691595776L);
		entity.setUserId(1506286500686622720L);
		userRoleDao.save(entity);
	}

	@Test
	void findAllTest() {
		List<UserRoleDo> all = userRoleDao.findAll();
		System.err.println("all = " + all);
	}
}