package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.AreaDo;

public interface AreaDao extends JpaRepository<AreaDo, Integer> {

	List<AreaDo> findAllByParentId(Integer pid);
}