package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amx.dataservice.model.domain.OrderDo;

public interface OrderDao extends JpaRepository<OrderDo, Long> {
	
	@Modifying // 表示修改方法
	@Query(nativeQuery = true,value = "UPDATE orders SET is_delete = 1,modify_time = NOW() WHERE id = :oid AND is_delete = 0")
	int logicalDelete(@Param("oid")long oid);

	List<OrderDo> findByIdIn(List<Long> idList);
	
}