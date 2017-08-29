package com.amx.dataservice.service;

import com.amx.dataservice.model.bo.UserBo;
import com.amx.dataservice.model.dto.UpdateResponseDto;


public interface UserService{
	
	UserBo findOne(long id);
	
	UpdateResponseDto save(UserBo bo);

	UpdateResponseDto update(UserBo bo);

	Object findOneByExample(UserBo example);
}
