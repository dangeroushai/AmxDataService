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

import com.amx.dataservice.dao.CartDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.CartBo;
import com.amx.dataservice.model.domain.CartDo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;
import com.amx.dataservice.service.CartService;
import com.amx.dataservice.util.BeanUtil;

@Service("CartService")
public class CartServiceImpl extends BaseService implements CartService {

	@Autowired
	private CartDao cartDao;

	/**
	 * 获取用户收藏产品列表
	 * @param id 产品ID
	 * @return
	 */
	@Deprecated
	public List<CartBo> findAllByUserId(int id) {
		List<CartBo> boList = null;
		List<CartDo> doList =  cartDao.getAllByUserId(id);
		if(doList != null){
			boList = new ArrayList<CartBo>();
			for(CartDo hdo : doList){
				boList.add(new CartBo(hdo));
			}
		}
		
		return boList;
	}

	/**
	 * 获取用户的所有收藏的产品id 
	 * @param id 用户id
	 * @return
	 */
	@Deprecated
	public List<Integer> getProductIdListByUserId(int id) {
		return cartDao.getProductIdListByUserId(id);
	}

	@Override
	public PageResponseDto<CartBo> findAllByQuery(PageQuery query) {
		List<CartBo> boList = null;
		
		//按时间降序排列
		Sort sort = new Sort(Direction.DESC, "createTime");
		//分页
		PageRequest pageRequest = new PageRequest(query.getPageIndex(), query.getPageSize(), sort ) ;
		
		//Specification<CartBo> spec = 
		//取样探针
		CartDo probe = new CartDo();
		probe.setUserId(query.getUserId());
		probe.setIsDelete(false);
		
		Example<CartDo> example = Example.of(probe);

		Page<CartDo> page = cartDao.findAll(example , pageRequest);
		List<CartDo> doList = page.getContent();
		
		//Do => Bo
		if(doList != null){
			boList = new ArrayList<CartBo>();
			for(CartDo hdo : doList){
				boList.add(new CartBo(hdo));
			}
		}
		
		return new PageResponseDto<CartBo>(page.getTotalElements(), page.getTotalPages(), query.getPageIndex(), boList);
	}
	
	@Override
	public UpdateResponseDto save(CartBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		CartDo entity = new CartDo();
		/*关键属性*/
		entity.setUserId(bo.getUserId());
		entity.setProductId(bo.getProductId());
		entity.setPackageId(bo.getPackageId());
		entity.setLanguageId(bo.getLanguageId());
		entity.setAdultNum(bo.getAdultNum());
		entity.setChildNum(bo.getChildNum());
		entity.setGoOffDate(bo.getGoOffDate());
		entity.setGoOffTime(bo.getGoOffTime());
		//entity.setTravelId()
		
		/*设置非空属性*/
		entity.setIsDelete(false);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		
		if(cartDao.saveAndFlush(entity ).getId() > 0){
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
			cartDao.delete(id);
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		} catch (Exception e) {
//			response.setCode(UpdateCodeEnum.FAIL.getCode());
			throw new RuntimeException(e);
		}
		return response;
	}
	
	@Override
	public UpdateResponseDto deleteInBatch(List<CartBo> boList){
		UpdateResponseDto response = new UpdateResponseDto(); 
		try {
			List<Long> cartIdList = new ArrayList<Long>();
			long userId = 0L;
			List<CartDo> entities = null;
			//FIXME - 将业务处理提取到业务服务中，保持数据服务的职责单一 
			for (CartBo bo : boList) {
				//获取UserId
				if(userId == 0L){
					userId = bo.getUserId();
				}
				cartIdList.add(bo.getId());
			}
			if(cartIdList.size() > 0){
				entities = new ArrayList<CartDo>();
				for(CartDo cdo : cartDao.findAll(cartIdList)){
					//检查是否属于当前用户，防止非法操作
					if(cdo.getUserId().equals(userId)){
						entities.add(cdo);
					}
				}
			}
			cartDao.deleteInBatch(entities);
			
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		} catch (Exception e) {
//			response.setCode(UpdateCodeEnum.FAIL.getCode());
			throw new RuntimeException(e);
		}
		return response;
	}

	@Override
	public Integer count(CartBo bo) {
		CartDo probe = new CartDo();
		probe.setUserId(bo.getUserId());
		probe.setIsDelete(false);
		
		Example<CartDo> example = Example.of(probe);
		return (int)cartDao.count(example);
	}

	@Override
	public UpdateResponseDto update(CartBo bo) {
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		CartDo entity = cartDao.findOne(bo.getId());
		/*关键属性*/
		BeanUtil.copyNotNullProperties(bo, entity);
		
		/*设置非空属性*/
		entity.setModifyTime(new Date());
		
		if(cartDao.save(entity ).getId() > 0){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}

	@Override
	public UpdateResponseDto updateInBatch(List<CartBo> boList) {
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		boolean isSuccess = true;

		for (CartBo cartBo : boList) {
			isSuccess = isSuccess && this.update(cartBo).getCode().equals(UpdateCodeEnum.SUCCESS.getCode());
		}
		
		response.setCode(isSuccess ? UpdateCodeEnum.SUCCESS.getCode() :  UpdateCodeEnum.FAIL.getCode());
		
		return response;
	}
	
	@Override
	public CartBo findOne(Long id) {
		CartBo bo = null;
		
		CartDo entity = cartDao.findOne(id);
		if(entity != null){
			bo = new CartBo(entity);
		}
		
		return bo;
	}
}
