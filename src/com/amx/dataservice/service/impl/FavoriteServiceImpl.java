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

import com.amx.dataservice.dao.FavoriteDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.FavoriteBo;
import com.amx.dataservice.model.domain.FavoriteDo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;
import com.amx.dataservice.service.FavoriteService;

@Service("favoriteService")
public class FavoriteServiceImpl extends BaseService implements FavoriteService {

	@Autowired
	private FavoriteDao favoriteDao;

	/**
	 * 获取用户收藏产品列表
	 * @param id 产品ID
	 * @return
	 */
	@Deprecated
	public List<FavoriteBo> findAllByUserId(int id) {
		List<FavoriteBo> boList = null;
		List<FavoriteDo> doList =  favoriteDao.getAllByUserId(id);
		if(doList != null){
			boList = new ArrayList<FavoriteBo>();
			for(FavoriteDo hdo : doList){
				boList.add(new FavoriteBo(hdo));
			}
		}
		
		return boList;
	}

	/**
	 * 获取用户的所有收藏的产品id 
	 * @param id 用户id
	 * @return
	 */
	@Override
	public List<Long> getProductIdListByUserId(long id) {
		return favoriteDao.getProductIdListByUserId(id);
	}

	@Override
	public PageResponseDto<FavoriteBo> findAllByQuery(PageQuery query) {
		List<FavoriteBo> boList = null;
		
		//按时间降序排列
		Sort sort = new Sort(Direction.DESC, "createTime");
		//分页
		PageRequest pageRequest = new PageRequest(query.getPageIndex(), query.getPageSize(), sort ) ;
		
		//Specification<FavoriteBo> spec = 
		//取样探针
		FavoriteDo probe = new FavoriteDo();
		probe.setUserId(query.getUserId());
		probe.setIsDelete(false);
		
		Example<FavoriteDo> example = Example.of(probe);

		Page<FavoriteDo> page = favoriteDao.findAll(example , pageRequest);
		List<FavoriteDo> doList = page.getContent();
		
		//Do => Bo
		if(doList != null){
			boList = new ArrayList<FavoriteBo>();
			for(FavoriteDo hdo : doList){
				boList.add(new FavoriteBo(hdo));
			}
		}
		
		return new PageResponseDto<FavoriteBo>(page.getTotalElements(), page.getTotalPages(), query.getPageIndex(), boList);
	}
	
	@Override
	public UpdateResponseDto save(FavoriteBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		FavoriteDo entity = new FavoriteDo();
		//关键属性
		entity.setProductId(bo.getProductId());
		entity.setUserId(bo.getUserId());
		
		//检查是否已经存在
		Example<FavoriteDo> example = Example.of(entity);
		if(favoriteDao.exists(example)){
			response.setCode(UpdateCodeEnum.EXIST.getCode());
		}else{
			//设置非空属性
			entity.setIsDelete(false);
			entity.setCreateTime(new Date());
			entity.setModifyTime(entity.getCreateTime());
			if(favoriteDao.saveAndFlush(entity ).getId() > 0){
				response.setCode(UpdateCodeEnum.SUCCESS.getCode());
			}else{
				response.setCode(UpdateCodeEnum.FAIL.getCode());
			}
		}
		
		return response;
	}
	
	@Override
	public UpdateResponseDto delete(FavoriteBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 
		try {
			FavoriteDo entity = new FavoriteDo();
			entity.setUserId(bo.getUserId());
			entity.setProductId(bo.getProductId());
			entity.setId(bo.getId());
			if(entity.getId() != null){//根据ID删除
				favoriteDao.delete(entity);
			}else{//先查找，再删除
				Example<FavoriteDo> example = Example.of(entity);
				FavoriteDo findOne = favoriteDao.findOne(example);
				if(findOne != null){
					favoriteDao.delete(findOne);
				}
			}
			
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		} catch (Exception e) {
//			response.setCode(UpdateCodeEnum.FAIL.getCode());
			throw new RuntimeException(e);
		}
		return response;
	}

}
