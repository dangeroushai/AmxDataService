package com.amx.dataservice.service;

import com.amx.dataservice.model.bo.TravelBo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;

public interface TravelService{
	

	PageResponseDto<TravelBo> findAllByQuery(PageQuery query);

	UpdateResponseDto delete(long id);

	UpdateResponseDto save(TravelBo bo);
	
	UpdateResponseDto update(TravelBo bo);

	TravelBo findOne(Long travelId);
}
