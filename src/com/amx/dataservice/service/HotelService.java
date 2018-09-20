package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.ProductBo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.qo.ProductQuery;

public interface HotelService{
	ProductBo findOne(long id);

	PageResponseDto<ProductBo> findAllByQuery(ProductQuery query);

	/**
	 * 获取指定目的地下的所有产品的属性ID（不含重复）
	 * @param destId
	 * @return
	 */
	List<Integer> getAllAttrIdsByDestId(Integer destId);

	/**
	 * 根据产品Id批量查询产品
	 * @param ids
	 * @return
	 */
	List<ProductBo> findAllByIdList(List<Long> ids);
}
