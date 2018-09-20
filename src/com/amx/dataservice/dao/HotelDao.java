package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amx.dataservice.model.domain.HotelDo;
import com.amx.dataservice.model.domain.ProductDo;

//JpaSpecificationExecutor - 复杂查询
public interface HotelDao extends JpaRepository<HotelDo, Long>,JpaSpecificationExecutor<HotelDo> {
	List<ProductDo> findByIdGreaterThanAndNameLike(Integer id, String name);
	
	
	@Query(nativeQuery = true, value = "SELECT * FROM hotel WHERE FIND_IN_SET( :destId ,dest_ids) AND sale_state = 1")
	List<ProductDo> findAllByDestinationId(@Param("destId")int destId);
	
	/**
	 * 根据Id查找产品
	 * @param ids
	 * @return
	 */
	List<ProductDo> findByIdIn(List<Long> ids);
}