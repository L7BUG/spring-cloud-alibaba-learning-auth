package com.byaoh.cloud.auth;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TestTemp
 *
 * @author luliangyu
 * @date 2021-11-30 13:49
 */
// @Transactional
// @Rollback
@SpringBootTest(classes = AuthApp.class)
public abstract class TestTemplate {
	// @MockBean
	// private ServerEndpointExporter serverEndpointExporter;
	//
	// @Autowired
	// private org.apache.shiro.mgt.SecurityManager securityManager;

	@BeforeEach
	public void supperBefore() throws Exception {
		// ThreadContext.bind(securityManager);
		// // 测试伪造登录
		// LoginUser user = new LoginUser();
		// user.setCurrentProject(UUID.randomUUID().toString().substring(16));
		// SecurityUtil.setUser(user);
	}
}
