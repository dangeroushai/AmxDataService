package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.RequirementDo;

public interface RequirementDao extends JpaRepository<RequirementDo, Long> {
}