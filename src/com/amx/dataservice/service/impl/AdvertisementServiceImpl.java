package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amx.dataservice.dao.AdvertisementDao;
import com.amx.dataservice.dao.AdvertisementTypeDao;
import com.amx.dataservice.model.bo.AdvertisementBo;
import com.amx.dataservice.model.domain.AdvertisementDo;
import com.amx.dataservice.model.domain.AdvertisementTypeDo;
import com.amx.dataservice.model.qo.AdvertisementQuery;
import com.amx.dataservice.service.AdvertisementService;

@Service("advertisementService")
public class AdvertisementServiceImpl extends BaseService implements AdvertisementService {

	@Autowired
	private AdvertisementDao adao;
	
	@Autowired
	private AdvertisementTypeDao aTypedao;

	@Override
	@Transactional(readOnly = true)
	public List<AdvertisementBo> findAllByQuery(AdvertisementQuery query) {
		List<AdvertisementBo> boList = null; 
		AdvertisementTypeDo probe = new AdvertisementTypeDo();
		probe.setUdid(query.getUdid());
		Example<AdvertisementTypeDo> example = Example.of(probe );
		AdvertisementTypeDo typeDo = aTypedao.findOne(example);
		if(typeDo != null){
			AdvertisementDo adProbe = new AdvertisementDo();
			adProbe.setAdTypeId(typeDo.getId());
			adProbe.setIsEnable(true);
			Example<AdvertisementDo> adExample = Example.of(adProbe );
			
			Sort sort = new Sort(new Order(Direction.ASC, "weight"),new Order(Direction.DESC, "id"));			
			List<AdvertisementDo> adDoList = adao.findAll(adExample, sort );
			if(adDoList != null){
				boList = new ArrayList<AdvertisementBo>();
				for(AdvertisementDo adDo : adDoList){
					AdvertisementBo adBo = new AdvertisementBo(adDo, typeDo);
					/*查找子广告*/
					AdvertisementDo adSubProbe = new AdvertisementDo();
					adSubProbe.setParentId(adDo.getId());
					adSubProbe.setIsEnable(true);
					Example<AdvertisementDo> adSubExample = Example.of(adSubProbe );
					List<AdvertisementDo> adDoSubList = adao.findAll(adSubExample, sort );
					if(adDoSubList != null && adDoSubList.size() != 0){
						adBo.setSubAdvertisementList(new ArrayList<AdvertisementBo>());
						//获取子广告的类型（类型必相同）
						AdvertisementTypeDo subTypeDo = aTypedao.getOne(adDoSubList.get(0).getAdTypeId());
						//填充子广告
						for(AdvertisementDo subAdDo :  adDoSubList){
							adBo.getSubAdvertisementList().add(new AdvertisementBo(subAdDo, subTypeDo)); 
						}
					}
					boList.add(adBo);
				}
			}
		} 
		
		return boList;
	}
}
