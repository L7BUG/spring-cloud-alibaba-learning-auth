package com.byaoh.cloud.auth.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	}

	@Test
	void findAllTest() {
		System.out.println(userDao.findAll());
	}
}