package com.amx.dataservice.service;

import com.amx.dataservice.model.bo.CurrencyBo;


public interface CurrencyService{
	
	CurrencyBo findOne(Integer id);
}
