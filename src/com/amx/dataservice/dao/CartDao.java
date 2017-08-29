package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amx.dataservice.model.domain.CartDo;

public interface CartDao extends JpaRepository<CartDo, Long> {
	
	/**
	 * 根据用户Id获取所有产品id 
	 * @param id 产品id
	 * @return
	 */
	@Query("SELECT h.productId FROM CartDo h WHERE h.userId = :uid AND h.isDelete = 0")
	List<Integer> getProductIdListByUserId(@Param("uid")long id);
	
	List<CartDo> getAllByUserId(long id);
	
}