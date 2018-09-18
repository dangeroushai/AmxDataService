package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.ReceiverAddressBo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;

public interface ReceiverAddressService{

	PageResponseDto<ReceiverAddressBo> findAllByQuery(PageQuery query);

	UpdateResponseDto save(ReceiverAddressBo bo);

	UpdateResponseDto delete(long id);

	Integer count(ReceiverAddressBo bo);

	UpdateResponseDto deleteInBatch(List<ReceiverAddressBo> boList);

	ReceiverAddressBo findOne(Long id);

}