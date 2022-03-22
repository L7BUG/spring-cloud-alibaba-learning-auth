package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.domain.dataobj.UserDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserDao
 *
 * @author l
 * @date 2022/3/22 下午5:21
 */
@Repository
public interface UserDao extends JpaRepository<UserDo, Long> {
	/**
	 * 通过用户名查询单条数据
	 *
	 * @param username 用户名
	 * @return 用户
	 */
	UserDo findByUsername(String username);
}
