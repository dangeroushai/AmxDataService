package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.FavoriteBo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;

public interface FavoriteService{
	

	PageResponseDto<FavoriteBo> findAllByQuery(PageQuery query);

	UpdateResponseDto save(FavoriteBo bo);

	UpdateResponseDto delete(FavoriteBo bo);

	List<Long> getProductIdListByUserId(long id);
}
