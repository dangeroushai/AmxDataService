package com.amx.dataservice.service;

import java.util.List;

import com.amx.dataservice.model.bo.AreaBo;

public interface AreaService {
	List<AreaBo> findAllByParentId(Integer pid);

}
