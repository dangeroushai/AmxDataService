package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amx.dataservice.model.domain.PackageDo;

public interface PackageDao extends JpaRepository<PackageDo, Long> {
	/**
	 * 获取产品的所有有效套餐 (按起售价个升序排列)
	 * @param id 产品id
	 * @return
	 */
	List<PackageDo> findByProductIdOrderByStartPrice(Integer pid);
	
	/**
	 * 获取产品的所有有效套餐的id 
	 * @param id 产品id
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT p.id FROM Package p WHERE p.product_Id = :pid AND p.sale_State = 1 AND p.is_delete = 0")
	List<Integer> getIdListByProductId(@Param("pid")long id);
	
	/**
	 * 获取所有套餐起售价在指定范围内的产品ID列表
	 * @param startPrice
	 * @param endPrice
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT DISTINCT p.product_id FROM Package p WHERE p.start_price BETWEEN :startPrice AND :endPrice AND p.sale_state = 1 AND p.is_delete = 0")
	List<Integer> getProductIdListByStartPriceBetween(@Param("startPrice")int startPrice, @Param("endPrice")int endPrice);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT p.product_id FROM Package p WHERE p.start_price >= :startPrice AND p.sale_state = 1 AND p.is_delete = 0")
	List<Integer> getProductIdListByStartPriceGreaterThan(@Param("startPrice")int startPrice);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT p.product_id FROM Package p WHERE p.start_price <= :endPrice AND p.sale_state = 1 AND p.is_delete = 0")
	List<Integer> getProductIdListByStartPriceLessThan(@Param("endPrice")int endPrice);
}