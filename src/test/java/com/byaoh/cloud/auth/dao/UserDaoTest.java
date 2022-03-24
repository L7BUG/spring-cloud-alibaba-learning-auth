package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.domain.dataobj.UserDo;
import com.byaoh.cloud.framework.enums.StatusEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootTest
class UserDaoTest {
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	void setUp() {
		System.out.println(userDao.getClass());
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void saveTest() {
		UserDo entity = new UserDo();
		String username = UUID.randomUUID().toString().substring(0, 32);
		entity.setUsername(username);
		entity.setPassword(passwordEncoder.encode(username));
		entity.setStatus(StatusEnum.ENABLE);
		userDao.save(entity);
	}

	@Test
	void findAllTest() {
	}
}