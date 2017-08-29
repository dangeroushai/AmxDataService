package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.RegionBo;


public interface RegionService{
	
	RegionBo findOne(Integer id);

	List<RegionBo> findAll(List<Integer> idList);
}
