package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amx.dataservice.model.domain.FavoriteDo;

public interface FavoriteDao extends JpaRepository<FavoriteDo, Long> {
	
	/**
	 * 根据用户Id获取所有产品id 
	 * @param id 产品id
	 * @return
	 */
	@Query("SELECT h.productId FROM FavoriteDo h WHERE h.userId = :uid AND h.isDelete = 0")
	List<Long> getProductIdListByUserId(@Param("uid")long id);
	
	List<FavoriteDo> getAllByUserId(int id);
}