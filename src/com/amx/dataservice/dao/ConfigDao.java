package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.ConfigDo;

public interface ConfigDao extends JpaRepository<ConfigDo, Integer> {
	
}