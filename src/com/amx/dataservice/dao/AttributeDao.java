package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.AttributeDo;

public interface AttributeDao extends JpaRepository<AttributeDo, Integer> {
}