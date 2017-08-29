package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.TravellerBo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;

public interface TravellerService{
	

	PageResponseDto<TravellerBo> findAllByOrderId(long orderId);

	UpdateResponseDto deleteByOrderId(long orderId);

	UpdateResponseDto save(List<TravellerBo> boList, long orderId);
}
