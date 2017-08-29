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

import com.amx.dataservice.dao.CommentDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.CommentBo;
import com.amx.dataservice.model.domain.CommentDo;
import com.amx.dataservice.model.domain.UserDo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.PageQuery;
import com.amx.dataservice.service.CommentService;
import com.amx.dataservice.util.StringUtil;

@Service("commentService")
public class CommentServiceImpl extends BaseService implements CommentService {

	@Autowired
	private CommentDao commentDao;


	@Override
	public PageResponseDto<CommentBo> findAllByQuery(PageQuery query) {
		List<CommentBo> boList = null;
		
		//按时间降序排列
		Sort sort = new Sort(Direction.DESC, "createTime");
		//分页
		PageRequest pageRequest = new PageRequest(query.getPageIndex(), query.getPageSize(), sort ) ;
		
		//取样探针
		CommentDo probe = new CommentDo();
		probe.setProductId(query.getProductId());
		probe.setIsDelete(false);
		probe.setIsEnable(true);
		
		Example<CommentDo> example = Example.of(probe);

		Page<CommentDo> page = commentDao.findAll(example , pageRequest);
		List<CommentDo> doList = page.getContent();
		
		//Do => Bo
		if(doList != null){
			boList = new ArrayList<CommentBo>();
			for(CommentDo cdo : doList){
				boList.add(new CommentBo(cdo));
			}
		}
		
		return new PageResponseDto<CommentBo>(page.getTotalElements(), page.getTotalPages(), query.getPageIndex(), boList);
	}
	
	@Override
	public UpdateResponseDto save(CommentBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		CommentDo entity = new CommentDo();
		/*关键属性*/
		entity.setUser(new UserDo(bo.getUserId()));
		entity.setOrderId(bo.getOrderId());
		entity.setProductId(bo.getProductId());
		entity.setContent(bo.getContent());
		entity.setPictures(StringUtil.mergeList2Str(bo.getPictureList()));
		
		/*设置非空属性*/
		entity.setIsEnable(true);
		entity.setIsDelete(false);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		
		if(commentDao.saveAndFlush(entity ).getId() > 0){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}
}
