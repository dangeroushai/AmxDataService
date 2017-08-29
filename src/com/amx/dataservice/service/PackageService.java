package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.PackageBo;

public interface PackageService{
	/**
	 * 获取产品套餐列表
	 * @param id 产品id
	 * @return
	 */
	List<PackageBo> findAllByProductId(Integer id);
	
	/**
	 * 获取产品的所有有效套餐的id 
	 * @param id 产品id
	 * @return
	 */
	List<Integer> getIdListByProductId(long id);
	
	PackageBo findOne(long id);

	List<Integer> getProductIdListByStartPriceBetween(Integer startPrice, Integer endPrice);

}
