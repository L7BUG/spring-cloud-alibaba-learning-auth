package com.byaoh.cloud.auth.controller;

import com.byaoh.cloud.common.CommonProperties;
import com.byaoh.cloud.common.CommonService;
import com.byaoh.cloud.framework.web.Result;
import com.byaoh.cloud.framework.web.ResultFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller
 *
 * @author l
 * @date 2022/3/15 下午10:30
 */
@RestController
@RequestMapping("/test")
public class TestController {
	private final CommonProperties commonProperties;
	private final CommonService commonService;

	public TestController(CommonProperties commonProperties, CommonService commonService) {
		this.commonProperties = commonProperties;
		this.commonService = commonService;
	}

	@GetMapping("/123")
	public Result<Long> webjars() {
		commonService.serr();
		return ResultFactory.success(commonProperties.getSystemUserId());
	}

	@PreAuthorize("@as.hasPermission('admin')")
	@GetMapping("/admin")
	public Result<String> admin() {
		return ResultFactory.success("admin");
	}

	@PreAuthorize("@as.hasPermission('system')")
	@GetMapping("/system")
	public Result<String> system() {
		return ResultFactory.success("system");
	}
}
