package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.CartBo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;

public interface CartService{
	

	PageResponseDto<CartBo> findAllByQuery(PageQuery query);

	UpdateResponseDto save(CartBo bo);

	UpdateResponseDto delete(long id);
	
	Integer count(CartBo bo);

	UpdateResponseDto deleteInBatch(List<CartBo> boList);

	UpdateResponseDto update(CartBo bo);

	CartBo findOne(Long id);

	UpdateResponseDto updateInBatch(List<CartBo> boList);
}
