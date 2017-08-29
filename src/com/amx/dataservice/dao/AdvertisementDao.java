package com.amx.dataservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amx.dataservice.model.domain.AdvertisementDo;

public interface AdvertisementDao extends JpaRepository<AdvertisementDo, Integer> {
	
	List<AdvertisementDo> findAllByAdTypeIdAndIsEnable(Integer typeId,boolean isEnable);
	
	List<AdvertisementDo> findAllByParentId(Integer parentId);
}