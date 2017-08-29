package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amx.dataservice.dao.TravelDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.TravelBo;
import com.amx.dataservice.model.domain.TravelDo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;
import com.amx.dataservice.service.TravelService;
import com.amx.dataservice.util.BeanUtil;

@Transactional
@Service("travelService")
public class TravelServiceImpl extends BaseService implements TravelService {

	@Autowired
	private TravelDao dao;

	@Transactional(readOnly = true)
	@Override
	public PageResponseDto<TravelBo> findAllByQuery(PageQuery query) {
		TravelDo probe = new TravelDo();
		probe.setUserId(query.getUserId());
		Example<TravelDo> example = Example.of(probe );
		
		//按时间降序排列
		Sort sort = new Sort(Direction.DESC, "createTime");
		//分页
		PageRequest pageRequest = new PageRequest(query.getPageIndex(), query.getPageSize(), sort ) ;
		
		Page<TravelDo> page = dao.findAll(example , pageRequest);
		
		List<TravelDo> doList = page.getContent();
		
		ArrayList<TravelBo> boList = null;
		//Do => Bo
		if(doList != null){
			boList = new ArrayList<TravelBo>();
			for(TravelDo cdo : doList){
				boList.add(new TravelBo(cdo));
			}
		}
		
		return new PageResponseDto<TravelBo>(page.getTotalElements(), page.getTotalPages(), query.getPageIndex(), boList);
	}

	@Override
	public UpdateResponseDto delete(long id) {
		UpdateResponseDto response = new UpdateResponseDto(); 
		try {
			dao.delete(id);
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		} catch (Exception e) {
//			response.setCode(UpdateCodeEnum.FAIL.getCode());
			throw new RuntimeException(e);
		}
		return response;
	}

	@Override
	public UpdateResponseDto save(TravelBo bo) {
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		TravelDo entity = new TravelDo();
		/*关键属性*/
		entity.setName(bo.getName());
		entity.setUserId(bo.getUserId());
		//检查当前用户是否有同名行程
		Example<TravelDo> example = Example.of(entity);
		if(dao.findOne(example ) != null){
			response.setCode(UpdateCodeEnum.EXIST.getCode());
			
			return response;
		}
		
		entity.setDayAmount(bo.getDayAmount());
		/*设置非空属性*/
		entity.setIsDelete(false);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		
		long id = dao.save(entity ).getId();
		if( id > 0){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
			response.setMsg(String.valueOf(id));
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}

	@Override
	public UpdateResponseDto update(TravelBo bo) {
		UpdateResponseDto response = new UpdateResponseDto(); 	
		
		TravelDo entity = dao.findOne(bo.getId());
		/*关键属性*/
		BeanUtil.copyNotNullProperties(bo, entity);
		
		/*设置非空属性*/
		entity.setModifyTime(new Date());
		
		if(dao.save(entity ).getId() > 0){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		return response;
	}

	@Override 
	public TravelBo findOne(Long travelId) {
		return new TravelBo(dao.findOne(travelId)) ;
	}
	
}
