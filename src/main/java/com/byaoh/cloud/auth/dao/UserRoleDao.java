package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.domain.dataobj.UserRoleDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 操作用户角色信息关联表
 *
 * @author l
 * @date 2022/3/22 下午10:44
 */
@Repository
public interface UserRoleDao extends JpaRepository<UserRoleDo, Long> {

}
