package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.CustomHodometerBo;
import com.amx.dataservice.model.dto.UpdateResponseDto;

public interface CustomHodometerService{
	
	/**
	 * 获取行程规划项列表
	 * @param id 用户ID
	 * @return
	 */
	List<CustomHodometerBo> findAllByUserId(long userId);

	List<CustomHodometerBo> findAllByTravelId(long travelId);
	
	UpdateResponseDto save(CustomHodometerBo bo);
	
	UpdateResponseDto update(CustomHodometerBo bo);
	
	UpdateResponseDto delete(List<Long> idList);

	CustomHodometerBo findOneByTravelId(Long travelId);

	UpdateResponseDto saveInBatch(List<CustomHodometerBo> boList);

	UpdateResponseDto updateInBatch(List<CustomHodometerBo> boList);

	UpdateResponseDto deleteByTravelId(Long id);
	
}
