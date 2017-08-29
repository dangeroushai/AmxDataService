package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amx.dataservice.model.domain.TravellerDo;

public interface TravellerDao extends JpaRepository<TravellerDo, Long> {
	
	@Modifying // 表示修改方法
	@Query(nativeQuery = true,value = "UPDATE traveller SET is_delete = 1,modify_time = NOW() WHERE order_id = :oid AND is_delete = 0")
	int logicalDeleteByOrderId(@Param("oid")long oid);
}