package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.domain.dataobj.RoleMenuDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色菜单dao
 *
 * @author l
 * @date 2022/3/24 下午7:02
 */
@Repository
public interface RoleMenuDao extends JpaRepository<RoleMenuDo, Long> {
}
