package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amx.dataservice.dao.AreaDao;
import com.amx.dataservice.model.bo.AreaBo;
import com.amx.dataservice.model.domain.AreaDo;
import com.amx.dataservice.service.AreaService;

@Service("areaService")
public class AreaServiceImpl extends BaseService implements AreaService {

	@Autowired
	private AreaDao dao;

	@Override
	public List<AreaBo> findAllByParentId(Integer pid) {
		// 顶级地区
		if (pid == null){
			pid = -1;
		}
		
		List<AreaBo> boList = null;
		List<AreaDo> doList = dao.findAllByParentId(pid);
		
		if(doList != null){
			boList = new ArrayList<AreaBo>(doList.size());
			for (AreaDo regionDo : doList) {
				boList.add(new AreaBo(regionDo));
			}
		}
		return boList;
	}
	

}
