package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.AttributeBo;


public interface AttributeService{
	/**
	 * 获取属性列表
	 * @param idList 为空则查找全部
	 * @return
	 */
	List<AttributeBo> findAllByIdList(List<Integer> idList);
	
	/**
	 * 获取所有属性列表
	 * @return
	 */
	List<AttributeBo> findAll();

	AttributeBo findOne(int id);

	List<AttributeBo> findAllByTypeId(Integer typeId);
}
