package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.domain.dataobj.MenuDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 菜单资源库
 *
 * @author l
 * @date 2022/3/23 下午9:59
 */
@Repository
public interface MenuDao extends JpaRepository<MenuDo, Long> {

}
