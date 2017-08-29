package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amx.dataservice.dao.LanguageDao;
import com.amx.dataservice.model.bo.LanguageBo;
import com.amx.dataservice.model.domain.LanguageDo;
import com.amx.dataservice.service.LanguageService;

@Service("languageService")
public class LanguageServiceImpl extends BaseService implements LanguageService {

	@Autowired
	private LanguageDao laguageDao;
	
	@Override
	public List<LanguageBo> findAllByIdList(List<Integer> idList) {
		List<LanguageBo> boList = null;

		List<LanguageDo> languageDoList = null;
		if (idList == null){
			//为空则查找全部
			languageDoList = laguageDao.findAll();
		} else{
			languageDoList = laguageDao.findAll(idList);
		}
		
		if(languageDoList != null){
			boList = new ArrayList<LanguageBo>();
			for(LanguageDo ldo : languageDoList){
				boList.add(new LanguageBo(ldo));
			}
		}
		
		return boList;
	}

	@Override
	public List<LanguageBo> findAll() {
		
		return this.findAllByIdList(null);
	}

	@Override
	public LanguageBo findOne(int id) {
		return new LanguageBo(laguageDao.findOne(id));
	}
}
