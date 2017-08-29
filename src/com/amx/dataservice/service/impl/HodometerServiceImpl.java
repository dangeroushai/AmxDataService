package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amx.dataservice.dao.HodometerDao;
import com.amx.dataservice.model.bo.HodometerBo;
import com.amx.dataservice.model.domain.HodometerDo;
import com.amx.dataservice.service.HodometerService;

@Service("hodometerService")
public class HodometerServiceImpl extends BaseService implements HodometerService {

	@Autowired
	private HodometerDao hodometerDao;

	@Override
	public List<HodometerBo> findAllByProductId(long id) {
		List<HodometerBo> boList = null;
		List<HodometerDo> doList =  hodometerDao.findByProductIdAndIsDeleteFalse(id);
		if(doList != null){
			boList = new ArrayList<HodometerBo>();
			for(HodometerDo hdo : doList){
				boList.add(new HodometerBo(hdo));
			}
		}
		
		return boList;
	}

	@Override
	public List<Integer> getIdListByProductId(long id) {
		return hodometerDao.getIdListByProductId(id);
	}
}
