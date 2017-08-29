package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.ProductExtDo;

public interface ProductExtDao extends JpaRepository<ProductExtDo, Long> {
	
}