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

import com.amx.dataservice.dao.ContactDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.ContactBo;
import com.amx.dataservice.model.domain.ContactDo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;
import com.amx.dataservice.service.ContactService;
import com.amx.dataservice.util.BeanUtil;
import com.amx.dataservice.util.StringUtil;

@Service("contactService")
public class ContactServiceImpl extends BaseService implements ContactService {

	@Autowired
	private ContactDao contactDao;


	@Override
	public PageResponseDto<ContactBo> findAllByQuery(PageQuery query) {
		List<ContactBo> boList = null;
		
		//按时间降序排列
		Sort sort = new Sort(Direction.DESC, "createTime");
		//分页
		PageRequest pageRequest = new PageRequest(query.getPageIndex(), query.getPageSize(), sort ) ;
		
		//取样探针
		ContactDo probe = new ContactDo();
		probe.setUserId(query.getUserId());
		probe.setIsDelete(false);
		
		Example<ContactDo> example = Example.of(probe);

		Page<ContactDo> page = contactDao.findAll(example , pageRequest);
		List<ContactDo> doList = page.getContent();
		
		//Do => Bo
		if(doList != null){
			boList = new ArrayList<ContactBo>();
			for(ContactDo cdo : doList){
				boList.add(new ContactBo(cdo));
			}
		}
		
		return new PageResponseDto<ContactBo>(page.getTotalElements(), page.getTotalPages(), query.getPageIndex(), boList);
	}
	
	@Override
	public UpdateResponseDto save(ContactBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		ContactDo entity = new ContactDo();
		/*关键属性*/
		entity.setUserId(bo.getUserId());
		entity.setId(bo.getId());
		entity.setEmail(bo.getEmail());
		entity.setFirstName(bo.getFirstName());
		entity.setLastName(bo.getLastName());
		entity.setGender(bo.getGender());
		entity.setPassport(bo.getPassport());
		if(StringUtil.isEmpty(bo.getCountryCode())){
			entity.setPhone(bo.getPhone());
		}else{
			entity.setPhone(bo.getCountryCode() + " " + bo.getPhone());
		}

		/*设置非空属性*/
		entity.setIsDelete(false);
		if(bo.getId() != null){//修改
			ContactDo findOne = contactDao.findOne(bo.getId());
			BeanUtil.copyNotNullProperties(entity, findOne);
			entity.setModifyTime(new Date());
			entity.setCreateTime(findOne.getCreateTime());
		}else{//新增
			entity.setCreateTime(new Date());
			entity.setModifyTime(entity.getCreateTime());
		}

		
		if(contactDao.saveAndFlush(entity ).getId() > 0){
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
			contactDao.delete(id);
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		} catch (Exception e) {
//			response.setCode(UpdateCodeEnum.FAIL.getCode());
			throw new RuntimeException(e);
		}
		return response;
	}
	
	@Override
	public UpdateResponseDto deleteInBatch(List<ContactBo> boList){
		UpdateResponseDto response = new UpdateResponseDto(); 
		try {
			List<Long> contactIdList = new ArrayList<Long>();
			long userId = 0L;
			List<ContactDo> entities = null;
			for (ContactBo bo : boList) {
				//获取UserId
				if(userId == 0L){
					userId = bo.getUserId();
				}
				contactIdList.add(bo.getId());
			}
			if(contactIdList.size() > 0){
				entities = new ArrayList<ContactDo>();
				for(ContactDo cdo : contactDao.findAll(contactIdList)){
					//检查是否属于当前用户，防止非法操作
					if(cdo.getUserId().equals(userId)){
						entities.add(cdo);
					}
				}
			}
			contactDao.deleteInBatch(entities);
			
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		} catch (Exception e) {
//			response.setCode(UpdateCodeEnum.FAIL.getCode());
			throw new RuntimeException(e);
		}
		return response;
	}
	
	@Override
	public Integer count(ContactBo bo) {
		ContactDo probe = new ContactDo();
		probe.setUserId(bo.getUserId());
		probe.setIsDelete(false);
		
		Example<ContactDo> example = Example.of(probe);
		return (int)contactDao.count(example);
	}

	@Override
	public ContactBo findOne(Long id) {
		return new ContactBo(contactDao.findOne(id));
	}
}
