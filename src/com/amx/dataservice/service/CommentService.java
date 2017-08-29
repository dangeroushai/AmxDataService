package com.amx.dataservice.service;

import com.amx.dataservice.model.bo.CommentBo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;

public interface CommentService{
	

	PageResponseDto<CommentBo> findAllByQuery(PageQuery query);

	UpdateResponseDto save(CommentBo bo);

}