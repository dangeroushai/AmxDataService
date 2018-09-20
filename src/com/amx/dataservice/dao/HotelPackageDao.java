package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amx.dataservice.model.domain.HotelPackageDo;

public interface HotelPackageDao extends JpaRepository<HotelPackageDo, Long> {
	/**
	 * 获取酒店的所有有效套餐 
	 * @param id 酒店id
	 * @return
	 */
	List<HotelPackageDo> findByHotelIdOrderByWeight(Integer pid);
	
	/**
	 * 获取产品的所有有效套餐的id 
	 * @param id 产品id
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT p.id FROM Package p WHERE p.product_Id = :pid AND p.sale_State = 1 AND p.is_delete = 0")
	List<Integer> getIdListByProductId(@Param("pid")long id);
	
}