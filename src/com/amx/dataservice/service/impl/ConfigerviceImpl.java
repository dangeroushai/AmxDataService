package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.amx.dataservice.dao.ConfigDao;
import com.amx.dataservice.model.bo.ConfigBo;
import com.amx.dataservice.model.domain.ConfigDo;
import com.amx.dataservice.service.ConfigService;

@Service("ConfigService")
public class ConfigerviceImpl extends BaseService implements ConfigService {

	@Autowired
	private ConfigDao dao;

	@Override
	public List<ConfigBo> findAll() {
		List<ConfigBo> configs = null;
		
		ConfigDo probe = new ConfigDo();
		probe.setIsDelete(false);
		probe.setIsEnable(true);
		
		Example<ConfigDo> example = Example.of(probe );
		
		List<ConfigDo> all = dao.findAll(example );
		if(all != null){
			configs = new ArrayList<ConfigBo>(all.size());
			for (ConfigDo configDo : all) {
				configs.add(new ConfigBo(configDo))	;
			}
		}
		
		return configs;
	}
}
