package com.byaoh.cloud.auth.dao;

import com.byaoh.cloud.auth.domain.dataobj.MenuDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单资源库
 *
 * @author l
 * @date 2022/3/23 下午9:59
 */
@Repository
public interface MenuDao extends JpaRepository<MenuDo, Long> {
	/**
	 * 获取全部根节点
	 *
	 * @return 根节点集合
	 */
	@Query("select m from MenuDo m where m.fatherId = -1L")
	List<MenuDo> findRootNodes();
}
