package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amx.dataservice.dao.RegionDao;
import com.amx.dataservice.model.bo.RegionBo;
import com.amx.dataservice.model.domain.RegionDo;
import com.amx.dataservice.service.RegionService;

@Service("regionService")
public class RegionServiceImpl extends BaseService implements RegionService {

	@Autowired
	private RegionDao dao;

	@Override
	public RegionBo findOne(Integer id) {
		return new RegionBo(dao.findOne(id));
	}

	@Override
	public List<RegionBo> findAll(List<Integer> idList) {
		List<RegionBo> boList = null;
		List<RegionDo> doList = null;
		if(idList == null){
			doList = dao.findAll();
		}else{
			doList = dao.findAll(idList);
		}
		if(doList != null){
			boList = new ArrayList<RegionBo>();
			for (RegionDo regionDo : doList) {
				boList.add(new RegionBo(regionDo));
			}
		}
		return boList;
	}
	

}
