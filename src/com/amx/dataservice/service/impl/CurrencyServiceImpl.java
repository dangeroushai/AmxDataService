package com.amx.dataservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amx.dataservice.dao.CurrencyDao;
import com.amx.dataservice.model.bo.CurrencyBo;
import com.amx.dataservice.service.CurrencyService;

@Service("currencyService")
public class CurrencyServiceImpl extends BaseService implements CurrencyService {

	@Autowired
	private CurrencyDao dao;

	@Override
	public CurrencyBo findOne(Integer id) {
		return new CurrencyBo(dao.findOne(id));
	}

}
