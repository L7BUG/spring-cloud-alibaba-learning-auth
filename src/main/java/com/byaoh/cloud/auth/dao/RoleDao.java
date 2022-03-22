package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.domain.dataobj.RoleDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色dao
 *
 * @author l
 * @date 2022/3/22 下午10:47
 */
@Repository
public interface RoleDao extends JpaRepository<RoleDo, Long> {

}
