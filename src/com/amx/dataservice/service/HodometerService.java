package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.HodometerBo;

public interface HodometerService{
	
	/**
	 * 获取行程列表
	 * @param id 产品ID
	 * @return
	 */
	List<HodometerBo> findAllByProductId(long id);
	
	/**
	 * 获取产品的所有有效行程的id 
	 * @param id 产品id
	 * @return
	 */
	List<Integer> getIdListByProductId(long id);
}
