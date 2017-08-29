package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amx.dataservice.dao.CustomHodometerDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.CustomHodometerBo;
import com.amx.dataservice.model.domain.CustomHodometerDo;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.service.CustomHodometerService;
import com.amx.dataservice.util.BeanUtil;

@Transactional
@Service("cutomHodometerService")
public class CustomHodometerServiceImpl extends BaseService implements CustomHodometerService {

	@Autowired
	private CustomHodometerDao dao;

	/**
	 * 表示行程项不属于任何行程
	 */
	private Long nullTravelId = 0L;
	
	@Transactional(readOnly = true)
	@Override
	public List<CustomHodometerBo> findAllByUserId(long userId) {
		List<CustomHodometerBo> boList = null;
		
		CustomHodometerDo probe = new CustomHodometerDo();
		probe.setUserId(userId);
		probe.setTravelId(nullTravelId);
		probe.setIsDelete(false);
		
		Example<CustomHodometerDo> example = Example.of(probe );
		List<CustomHodometerDo> doList =  dao.findAll(example);
		if(doList != null){ 
			boList = new ArrayList<CustomHodometerBo>();
			for(CustomHodometerDo hdo : doList){
				boList.add(new CustomHodometerBo(hdo));
			}
		}
		
		return boList;
	}
	

	@Transactional(readOnly = true)
	@Override
	public CustomHodometerBo findOneByTravelId(Long travelId) {
		CustomHodometerBo bo = null;
		
		CustomHodometerDo probe = new CustomHodometerDo();
		probe.setTravelId(travelId);
		probe.setIsDelete(false);
		
		/*List<Order> orders = new ArrayList<Sort.Order>();
		orders.add(new Order(Direction.ASC, "goOffDate"));
		orders.add(new Order(Direction.ASC, "goOffTime"));
		Sort sort = new Sort(orders );*/
		
		Example<CustomHodometerDo> example = Example.of(probe );
		CustomHodometerDo chdo =  dao.findAll(example).get(0);
		if(chdo != null){ 
			bo = new CustomHodometerBo(chdo);
		}
		
		return bo;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<CustomHodometerBo> findAllByTravelId(long travelId) {
		List<CustomHodometerBo> boList = null;
		
		CustomHodometerDo probe = new CustomHodometerDo();
		probe.setTravelId(travelId);
		probe.setIsDelete(false);
		
		List<Order> orders = new ArrayList<Sort.Order>();
		orders.add(new Order(Direction.ASC, "goOffDate"));
		orders.add(new Order(Direction.ASC, "goOffTime"));
		Sort sort = new Sort(orders );
		
		Example<CustomHodometerDo> example = Example.of(probe );
		List<CustomHodometerDo> doList =  dao.findAll(example,sort);
		if(doList != null){ 
			boList = new ArrayList<CustomHodometerBo>();
			for(CustomHodometerDo hdo : doList){
				boList.add(new CustomHodometerBo(hdo));
			}
		}
		
		return boList;
	}
	
	@Override
	public UpdateResponseDto save(CustomHodometerBo bo) {
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		CustomHodometerDo entity = new CustomHodometerDo();
		/*关键属性*/
		BeanUtils.copyProperties(bo, entity);
		if(entity.getTravelId() == null){
			entity.setTravelId(nullTravelId);
		}
		
		/*设置非空属性*/
		entity.setIsDelete(false);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		
		long id = dao.save(entity ).getId();
		if(id > 0){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}

	@Override
	public UpdateResponseDto update(CustomHodometerBo bo) {
		UpdateResponseDto response = new UpdateResponseDto(); 

		CustomHodometerDo odo = dao.findOne(bo.getId());
		BeanUtil.copyNotNullProperties(bo, odo);
		odo.setModifyTime(new Date());
		
		if(dao.save(odo) != null){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}

	@Override
	public UpdateResponseDto delete(List<Long> idList) {
		UpdateResponseDto response = new UpdateResponseDto();
		
		if(dao.deleteByIdIn(idList) > 0){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}


	@Override
	public UpdateResponseDto saveInBatch(List<CustomHodometerBo> boList) {
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		List<CustomHodometerDo> doList = new ArrayList<CustomHodometerDo>();
		for (CustomHodometerBo customHodometerBo : boList) {
			CustomHodometerDo entity = new CustomHodometerDo();
			/*关键属性*/
			BeanUtils.copyProperties(customHodometerBo, entity);
			if(entity.getTravelId() == null){
				entity.setTravelId(nullTravelId);
			}
			
			/*设置非空属性*/
			entity.setIsDelete(false);
			entity.setCreateTime(new Date());
			entity.setModifyTime(entity.getCreateTime());
			
			doList.add(entity);
		}
		
		if(dao.save(doList ) != null){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}
	
	@Override
	public UpdateResponseDto updateInBatch(List<CustomHodometerBo> boList) {
		UpdateResponseDto response = new UpdateResponseDto(); 

		List<CustomHodometerDo> doList = new ArrayList<CustomHodometerDo>();
		for (CustomHodometerBo customHodometerBo : boList) {
			CustomHodometerDo odo = dao.findOne(customHodometerBo.getId());
			BeanUtil.copyNotNullProperties(customHodometerBo, odo);
			odo.setModifyTime(new Date());
			
			doList.add(odo);
		}
		
		if(dao.save(doList) != null){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}


	@Override
	public UpdateResponseDto deleteByTravelId(Long travelId) {
		UpdateResponseDto response = new UpdateResponseDto();
		
		if(dao.deleteByTravelId(travelId) > 0){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}

}
