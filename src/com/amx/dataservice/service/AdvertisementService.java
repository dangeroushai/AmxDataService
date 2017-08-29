package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.AdvertisementBo;
import com.amx.dataservice.model.qo.AdvertisementQuery;


public interface AdvertisementService{
	/**
	 * 获取广告列表
	 * @param idList 调用标识
	 * @return
	 */
	List<AdvertisementBo> findAllByQuery(AdvertisementQuery query);
	
}
