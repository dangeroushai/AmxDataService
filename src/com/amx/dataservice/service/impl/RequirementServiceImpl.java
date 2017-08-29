package com.amx.dataservice.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amx.dataservice.dao.RequirementDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.RequirementBo;
import com.amx.dataservice.model.domain.RequirementDo;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.service.RequirementService;

@Service("requirementService")
public class RequirementServiceImpl extends BaseService implements RequirementService {

	@Autowired
	private RequirementDao dao;
	
	@Override
	@Transactional
	public UpdateResponseDto save(RequirementBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		RequirementDo entity = new RequirementDo();
		/*关键属性*/
		BeanUtils.copyProperties(bo, entity);
		
		/*设置非空属性*/
		entity.setIsDelete(false);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		
		if( dao.saveAndFlush(entity ) != null){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}
}
