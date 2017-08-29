package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amx.dataservice.model.domain.HodometerDo;

public interface HodometerDao extends JpaRepository<HodometerDo, Long> {
	List<HodometerDo> findByProductIdAndIsDeleteFalse(Long pid);
	
	/**
	 * 获取产品的所有有效套餐的id 
	 * @param id 产品id
	 * @return
	 */
	@Query("SELECT h.id FROM HodometerDo h WHERE h.productId = :pid AND h.isDelete = 0")
	List<Integer> getIdListByProductId(@Param("pid")long id);
}