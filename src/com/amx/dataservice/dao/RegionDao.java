package com.amx.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.RegionDo;

public interface RegionDao extends JpaRepository<RegionDo, Integer> {
}