package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.AdvertisementTypeDo;

public interface AdvertisementTypeDao extends JpaRepository<AdvertisementTypeDo, Integer> {
	
	List<AdvertisementTypeDo> findAllByParentId(Integer parentId);
}