package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.LanguageBo;


public interface LanguageService{
	/**
	 * 获取语言列表
	 * @param idList 为空则查找全部
	 * @return
	 */
	List<LanguageBo> findAllByIdList(List<Integer> idList);
	
	/**
	 * 获取所有语言列表
	 * @return
	 */
	List<LanguageBo> findAll();

	LanguageBo findOne(int id);
}
