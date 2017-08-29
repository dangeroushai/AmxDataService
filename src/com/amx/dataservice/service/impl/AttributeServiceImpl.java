package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.amx.dataservice.dao.AttributeDao;
import com.amx.dataservice.model.bo.AttributeBo;
import com.amx.dataservice.model.domain.AttributeDo;
import com.amx.dataservice.service.AttributeService;

@Service("attributeService")
public class AttributeServiceImpl extends BaseService implements AttributeService {

	@Autowired
	private AttributeDao dao;
	
	@Override
	public List<AttributeBo> findAllByIdList(List<Integer> idList) {
		List<AttributeBo> boList = null;

		List<AttributeDo> AttributeDoList = null;
		if (idList == null){
			//为空则查找全部
			AttributeDoList = dao.findAll();
		} else{
			AttributeDoList = dao.findAll(idList);
		}
		
		if(AttributeDoList != null){
			boList = new ArrayList<AttributeBo>();
			for(AttributeDo ldo : AttributeDoList){
				boList.add(new AttributeBo(ldo));
			}
		}
		
		return boList;
	}

	@Override
	public List<AttributeBo> findAll() {
		
		return this.findAllByIdList(null);
	}

	@Override
	public AttributeBo findOne(int id) {
		return new AttributeBo(dao.findOne(id));
	}

	@Override
	public List<AttributeBo> findAllByTypeId(Integer typeId) {
		List<AttributeBo> boList = null;

		List<AttributeDo> AttributeDoList = null;
		
		AttributeDo probe = new AttributeDo();
		probe.setIsDelete(false);
		probe.setIsEnable(true);
		probe.setTypeId(typeId);
		Example<AttributeDo> example = Example.of(probe );
		Sort sort = new Sort("weight");
		
		AttributeDoList = dao.findAll(example, sort);
				
		if(AttributeDoList != null){
			boList = new ArrayList<AttributeBo>();
			for(AttributeDo ldo : AttributeDoList){
				boList.add(new AttributeBo(ldo));
			}
		}
		
		return boList;
	}
}
