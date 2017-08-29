package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.OrderBo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.OrderQuery;


public interface OrderService{

	long getProductIdById(long id);

	OrderBo findOne(long id);

	PageResponseDto<OrderBo> findAllByQuery(OrderQuery query);
	
	List<OrderBo> findAllByIdList(List<Long> idList);

	UpdateResponseDto delete(long id);

	UpdateResponseDto save(OrderBo bo);

	UpdateResponseDto update(OrderBo bo);

	UpdateResponseDto saveInBatch(List<OrderBo> boList);

	UpdateResponseDto deleteInBatch(List<Long> idList);

	List<OrderBo> findAllByTradeNo(String tradeNo);

	UpdateResponseDto updateInBatch(List<OrderBo> boList);

}