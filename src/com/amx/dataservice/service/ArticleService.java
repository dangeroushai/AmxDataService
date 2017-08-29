package com.amx.dataservice.service;

import com.amx.dataservice.model.bo.ArticleBo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.qo.ArticleQuery;

public interface ArticleService{
	ArticleBo findOne(Integer id);

	PageResponseDto<ArticleBo> findAllByQuery(ArticleQuery query);
}
