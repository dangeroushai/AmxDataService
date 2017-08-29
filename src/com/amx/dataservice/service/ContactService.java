package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.ContactBo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;

public interface ContactService{

	PageResponseDto<ContactBo> findAllByQuery(PageQuery query);

	UpdateResponseDto save(ContactBo bo);

	UpdateResponseDto delete(long id);

	Integer count(ContactBo bo);

	UpdateResponseDto deleteInBatch(List<ContactBo> boList);

	ContactBo findOne(Long id);

}