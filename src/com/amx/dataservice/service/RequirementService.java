package com.amx.dataservice.service;

import com.amx.dataservice.model.bo.RequirementBo;
import com.amx.dataservice.model.dto.UpdateResponseDto;



public interface RequirementService{

	UpdateResponseDto save(RequirementBo bo);

}