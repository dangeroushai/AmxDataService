package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amx.dataservice.dao.ArticleDao;
import com.amx.dataservice.enums.ArticleTypeEnum;
import com.amx.dataservice.model.bo.ArticleBo;
import com.amx.dataservice.model.domain.ArticleDo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.qo.ArticleQuery;
import com.amx.dataservice.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl extends BaseService implements ArticleService {

	@Autowired
	private ArticleDao dao;

	@Override
	public ArticleBo findOne(Integer id) {
		return new ArticleBo(dao.findOne(id));
	}

	@Override
	public PageResponseDto<ArticleBo> findAllByQuery(ArticleQuery query) {
		List<ArticleBo> boList = null;
		
		/*//按时间降序排列
		List<Order> sortList  = new ArrayList<Order>();
		sortList.add(new Order(Direction.DESC, "createTime"));
		sortList.add(new Order("weight"));
		
		Sort sort = new Sort(sortList);
		//分页
		PageRequest pageRequest = new PageRequest(query.getPageIndex(), query.getPageSize(), sort ) ;
		
		//取样探针
		ArticleDo probe = new ArticleDo();
		probe.setAttrIds(String.valueOf(query.getTypeId()));
		probe.setIsEnable(true);
		probe.setIsDelete(false);
		
		Example<ArticleDo> example = Example.of(probe);

		Page<ArticleDo> page = dao.findAll(example , pageRequest);*/
		
		long count = 0;
		//为空则查找所有漫行纪文章
		if(query.getTypeId() == null || query.getTypeId() == 0){
			query.setTypeId(ArticleTypeEnum.TRAVEL_NOTE.getAttrId());
		}
		count += dao.count(query.getTypeId());
		
		int pageCount = (int) (count % query.getPageSize() == 0 ? count / query.getPageSize() : count / query.getPageSize() + 1);
		List<ArticleDo> doList = dao.findAll(query.getTypeId() , query.getPageIndex() * query.getPageSize(),query.getPageSize());
		
		//Do => Bo
		if(doList != null){
			boList = new ArrayList<ArticleBo>();
			for(ArticleDo ado : doList){
				boList.add(new ArticleBo(ado));
			}
		}
		return new PageResponseDto<ArticleBo>(count, pageCount, query.getPageIndex(), boList);
	}
}
