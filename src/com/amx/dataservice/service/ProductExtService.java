package com.amx.dataservice.service;

import com.amx.dataservice.model.bo.ProductExtBo;

public interface ProductExtService{
	ProductExtBo findOneByProductId(long productId);
}
