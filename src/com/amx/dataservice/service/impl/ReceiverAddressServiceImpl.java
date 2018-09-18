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

import com.amx.dataservice.dao.ReceiverAddressDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.ReceiverAddressBo;
import com.amx.dataservice.model.domain.ReceiverAddressDo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;
import com.amx.dataservice.service.ReceiverAddressService;
import com.amx.dataservice.util.BeanUtil;

@Service("receiverAddressService")
public class ReceiverAddressServiceImpl extends BaseService implements ReceiverAddressService {

	@Autowired
	private ReceiverAddressDao receiverAddressDao;


	@Override
	public PageResponseDto<ReceiverAddressBo> findAllByQuery(PageQuery query) {
		List<ReceiverAddressBo> boList = null;
		
		//按时间降序排列
		Sort sort = new Sort(Direction.DESC, "createTime");
		//分页
		PageRequest pageRequest = new PageRequest(query.getPageIndex(), query.getPageSize(), sort ) ;
		
		//取样探针
		ReceiverAddressDo probe = new ReceiverAddressDo();
		probe.setUserId(query.getUserId());
		probe.setIsDelete(false);
		
		Example<ReceiverAddressDo> example = Example.of(probe);

		Page<ReceiverAddressDo> page = receiverAddressDao.findAll(example , pageRequest);
		List<ReceiverAddressDo> doList = page.getContent();
		
		//Do => Bo
		if(doList != null){
			boList = new ArrayList<ReceiverAddressBo>();
			for(ReceiverAddressDo cdo : doList){
				boList.add(new ReceiverAddressBo(cdo));
			}
		}
		
		return new PageResponseDto<ReceiverAddressBo>(page.getTotalElements(), page.getTotalPages(), query.getPageIndex(), boList);
	}
	
	@Override
	public UpdateResponseDto save(ReceiverAddressBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		ReceiverAddressDo entity = new ReceiverAddressDo();
		/*关键属性*/
		entity.setUserId(bo.getUserId());
		entity.setId(bo.getId());
		entity.setFirstName(bo.getFirstName());
		entity.setLastName(bo.getLastName());
		entity.setPhone(bo.getPhone());

		/*设置非空属性*/
		entity.setIsDelete(false);
		if(bo.getId() != null){//修改
			ReceiverAddressDo findOne = receiverAddressDao.findOne(bo.getId());
			BeanUtil.copyNotNullProperties(entity, findOne);
			entity.setModifyTime(new Date());
			entity.setCreateTime(findOne.getCreateTime());
		}else{//新增
			entity.setCreateTime(new Date());
			entity.setModifyTime(entity.getCreateTime());
		}

		
		if(receiverAddressDao.saveAndFlush(entity ).getId() > 0){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}
	
	@Override
	public UpdateResponseDto delete(long id){
		UpdateResponseDto response = new UpdateResponseDto(); 
		try {
			receiverAddressDao.delete(id);
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		} catch (Exception e) {
//			response.setCode(UpdateCodeEnum.FAIL.getCode());
			throw new RuntimeException(e);
		}
		return response;
	}
	
	@Override
	public UpdateResponseDto deleteInBatch(List<ReceiverAddressBo> boList){
		UpdateResponseDto response = new UpdateResponseDto(); 
		try {
			List<Long> ReceiverAddressIdList = new ArrayList<Long>();
			long userId = 0L;
			List<ReceiverAddressDo> entities = null;
			for (ReceiverAddressBo bo : boList) {
				//获取UserId
				if(userId == 0L){
					userId = bo.getUserId();
				}
				ReceiverAddressIdList.add(bo.getId());
			}
			if(ReceiverAddressIdList.size() > 0){
				entities = new ArrayList<ReceiverAddressDo>();
				for(ReceiverAddressDo cdo : receiverAddressDao.findAll(ReceiverAddressIdList)){
					//检查是否属于当前用户，防止非法操作
					if(cdo.getUserId().equals(userId)){
						entities.add(cdo);
					}
				}
			}
			receiverAddressDao.deleteInBatch(entities);
			
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		} catch (Exception e) {
//			response.setCode(UpdateCodeEnum.FAIL.getCode());
			throw new RuntimeException(e);
		}
		return response;
	}
	
	@Override
	public Integer count(ReceiverAddressBo bo) {
		ReceiverAddressDo probe = new ReceiverAddressDo();
		probe.setUserId(bo.getUserId());
		probe.setIsDelete(false);
		
		Example<ReceiverAddressDo> example = Example.of(probe);
		return (int)receiverAddressDao.count(example);
	}

	@Override
	public ReceiverAddressBo findOne(Long id) {
		return new ReceiverAddressBo(receiverAddressDao.findOne(id));
	}
}
