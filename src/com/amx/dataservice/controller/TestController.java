package com.amx.dataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amx.dataservice.model.qo.ProductQuery;
import com.amx.dataservice.service.ProductService;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	
	@Autowired
	private ProductService Service;
	
	@ResponseBody
	@RequestMapping()
	public Object index(){
		/*PageQuery query = new PageQuery();
		query.setPageIndex(0);
		query.setPageSize(2);
		query.setUserId(1L);
		query.setProductId(1L);*/
		
		/*List<Long> idList = new ArrayList<Long>();
		idList.add(1L);
		idList.add(2L);
		return Service.delete(idList );*/
		
		
		ProductQuery query = new ProductQuery();
/*		query.setKeyword("keyword");
		query.setTypeId(1);
		query.setStartPrice(1);
		query.setThemeId(2);
		query.setSceneId(4);
		query.setDestinationId(3);
		query.setEndPrice(1000);
		query.setPageIndex(0);
		query.setPageSize(20);*/
		
		return Service.findAllByQuery(query );
		
	}

}
