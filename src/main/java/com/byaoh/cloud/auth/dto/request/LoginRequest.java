package com.byaoh.cloud.auth.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登陆实体
 *
 * @author l
 * @date 2022/3/22 下午5:25
 */
@Data
public class LoginRequest implements Serializable {
	private static final long serialVersionUID = -3570550392935355862L;

	private String username;
	
	private String password;
}
