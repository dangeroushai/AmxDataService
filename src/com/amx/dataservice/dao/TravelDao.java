package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.TravelDo;

public interface TravelDao extends JpaRepository<TravelDo, Long> {
	
}