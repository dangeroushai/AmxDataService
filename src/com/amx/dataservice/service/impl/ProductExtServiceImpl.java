package com.amx.dataservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amx.dataservice.dao.ProductExtDao;
import com.amx.dataservice.model.bo.ProductExtBo;
import com.amx.dataservice.model.domain.ProductExtDo;
import com.amx.dataservice.service.ProductExtService;

@Service("productExtService")
public class ProductExtServiceImpl extends BaseService implements ProductExtService {

	@Autowired
	private ProductExtDao productExtDao;


	@Override
	@Transactional(readOnly = true)
	public ProductExtBo findOneByProductId(long productId) {
		
		ProductExtDo probe = new ProductExtDo();
		probe.setProductId(productId);
		probe.setIsDelete(false);
		
		Example<ProductExtDo> example = Example.of(probe );
		productExtDao.findOne(example);
		
		return null;
	}
}


