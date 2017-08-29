package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amx.dataservice.dao.TravellerDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.TravellerBo;
import com.amx.dataservice.model.domain.TravellerDo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.service.TravellerService;

@Service("travellerService")
public class TravellerServiceImpl extends BaseService implements TravellerService {

	@Autowired
	private TravellerDao travellerDao;

	@Override
	public PageResponseDto<TravellerBo> findAllByOrderId(long orderId) {
		List<TravellerBo> boList = null;
		
		//按时间降序排列
		Sort sort = new Sort(Direction.DESC, "createTime");
		//取样探针
		TravellerDo probe = new TravellerDo();
		probe.setOrderId(orderId);
		probe.setIsDelete(false);
		
		Example<TravellerDo> example = Example.of(probe);

		List<TravellerDo> doList = travellerDao.findAll(example, sort);
		
		//Do => Bo
		if(doList != null){
			boList = new ArrayList<TravellerBo>();
			for(TravellerDo hdo : doList){
				boList.add(new TravellerBo(hdo));
			}
		}
		
		return new PageResponseDto<TravellerBo>(Long.valueOf(doList.size()), 1, 0, boList);
	}
	
	@Override
	@Transactional
	public UpdateResponseDto save(List<TravellerBo> boList, long orderId){
		UpdateResponseDto response = new UpdateResponseDto();
		
		List<TravellerDo> doList = new ArrayList<TravellerDo>();
		for (TravellerBo bo : boList) {
			TravellerDo entity = new TravellerDo();
			/*关键属性*/
			entity.setEmail(bo.getEmail());
			entity.setFirstName(bo.getFirstName());
			entity.setGender(bo.getGender());
			entity.setIsPrincipal(bo.getIsPrincipal());
			entity.setLastName(bo.getLastName());
			entity.setOrderId(orderId);
			entity.setPassport(bo.getPassport());
			entity.setPhone(bo.getPhone());
			
			/*设置非空属性*/
			entity.setIsDelete(false);
			entity.setCreateTime(new Date());
			entity.setModifyTime(entity.getCreateTime());
			
			doList.add(entity);
		}
		
		
		if(! travellerDao.save(doList ).isEmpty()){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
			
			throw new RuntimeException("出行人持久化失败");
		}
		
		return response;
	}
	
	@Override
	@Transactional
	public UpdateResponseDto deleteByOrderId(long orderId) {
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		if(travellerDao.logicalDeleteByOrderId(orderId) > 0 ){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		} else  {
			response.setCode(UpdateCodeEnum.FAIL.getCode());
			
			logger.error("订单号["+ orderId +"]出行人删除失败");
		}
		return response;
	}
}
