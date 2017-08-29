package com.amx.dataservice.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amx.dataservice.dao.updateTable.MiddleCarDao;
import com.amx.dataservice.dao.updateTable.MiddleFreeDao;
import com.amx.dataservice.dao.updateTable.MiddleLineDao;
import com.amx.dataservice.dao.updateTable.MiddleTravelDao;
import com.amx.dataservice.dao.updateTable.MiddleTravelTmpDao;
import com.amx.dataservice.dao.updateTable.PHPSerialize;
import com.amx.dataservice.dao.updateTable.PHPValue;
import com.amx.dataservice.model.domain.updateTable.MiddleCar;
import com.amx.dataservice.model.domain.updateTable.MiddleFree;
import com.amx.dataservice.model.domain.updateTable.MiddleLine;
import com.amx.dataservice.model.domain.updateTable.MiddleTravel;
import com.amx.dataservice.model.domain.updateTable.MiddleTravelTmp;

@Controller
@RequestMapping("/updatetable")
public class UpdateTableSevice {
	
	@Autowired
	MiddleLineDao linedao;
	@Autowired
	MiddleFreeDao freedao;
	@Autowired
	MiddleCarDao cardao;
	@Autowired
	MiddleTravelDao traveldao;
	@Autowired
	MiddleTravelTmpDao travelTmpdao;
	
	
	@ResponseBody
	@RequestMapping("/sline_car")
	public void sline_car() throws Exception{
		List<MiddleCar> findAll = cardao.findAll();
		for (MiddleCar middleProduct : findAll) {
			/* attrid */
			middleProduct.setAttrid( attridHandler(middleProduct.getAttrid()));
			/* 反序列化related */
			middleProduct.setRelated(recommendIdsHandler(middleProduct.getRelated()));
			/* 处理售卖规则 */
			middleProduct.setSaleRule(saleRuleHandler(middleProduct.getSaleRule()));
		}
		
		cardao.save(findAll);
	}
	
	@ResponseBody
	@RequestMapping("/sline_free")
	public void sline_free() throws Exception{
		List<MiddleFree> findAll = freedao.findAll();
		for (MiddleFree middleProduct : findAll) {
			/* attrid */
			middleProduct.setAttrid( attridHandler(middleProduct.getAttrid()));
		}
		
		freedao.save(findAll);
	}
	
	
	@ResponseBody
	@RequestMapping("/sline_travel")
	public void sline_travel() throws Exception{
		List<MiddleTravel> findAll = traveldao.findAll();
		for (MiddleTravel middleProduct : findAll) {
			/* attrid */
			middleProduct.setAttrid( attridHandler(middleProduct.getAttrid()));
			/* 反序列化related */
			middleProduct.setRelated(recommendIdsHandler(middleProduct.getRelated()));
			/* 处理售卖规则 */
			middleProduct.setSaleRule(saleRuleHandler(middleProduct.getSaleRule()));
		}
		
		traveldao.save(findAll);
	}
	
	@ResponseBody
	@RequestMapping("/sline_travel_tmp")
	public void sline_travel_tmp() throws Exception{
		
		List<Order> orders = new ArrayList<Sort.Order>();
		orders.add(new Order(Direction.ASC, "productId"));
		orders.add(new Order(Direction.ASC, "dayOrder"));
		orders.add(new Order(Direction.ASC, "sdate"));
		Sort sort = new Sort(orders );
		
		List<MiddleTravelTmp> findAll = travelTmpdao.findAll(sort);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Integer current_productId = null; 
		Integer current_dayOrder = null; 
		Integer current_itemOrder = null; 
		for (MiddleTravelTmp middleProduct : findAll) {
			//开始处理一个新行程
			if(!middleProduct.getProductId().equals(current_productId)){
				current_productId = middleProduct.getProductId(); 
				current_dayOrder = null; 
				current_itemOrder = null; 
			}
			//处理同一行程的新一天
			if(!middleProduct.getDayOrder().equals(current_dayOrder)){
				current_dayOrder = middleProduct.getDayOrder();
				current_itemOrder = 0;
			}
			middleProduct.setGoOffTime(sdf.format(new Date(middleProduct.getSdate() * 1000)));
			middleProduct.setItemOrder(current_itemOrder++);
		}
		
		travelTmpdao.save(findAll);
	}
	
	
	@ResponseBody
	@RequestMapping("/sline_line")
	public void sline_line() throws Exception{
		List<MiddleLine> findAll = linedao.findAll();
		for (MiddleLine middleProduct : findAll) {
			/* attrid */
			middleProduct.setAttrid( attridHandler(middleProduct.getAttrid()));
			/* 反序列化related */
			middleProduct.setRelated(recommendIdsHandler(middleProduct.getRelated()));
			/* 处理售卖规则 */
			middleProduct.setSaleRule(saleRuleHandler(middleProduct.getSaleRule()));
		}
		
		linedao.save(findAll);
	}
	
	
	public static String attridHandler(String src){
		String new_attrid = "";
		if(src != null){
			String[] split = src.split(",");
			for (String string : split) {
				new_attrid += Integer.valueOf(string) + 20;
				new_attrid += ",";
			}
		}
		
		return new_attrid;
	}
	
	/**
	 * 处理sale_rule 字段
	 * @param src
	 * @return
	 */
	public static String saleRuleHandler(String src){
		int[] res = new int[7];
		for (int i = 0; i < res.length; i++) {
			res[i] = 0;
		}
		String[] split = src.split(",");
		for (String string : split) {
			res[Integer.parseInt(string)] = 1;
		}
		String s = "";
		for (int i = 0; i < res.length; i++) {
			if(res[i] == 1){
				s += "1";
			}else{
				s += "0";
			}
		}
		
		return String.valueOf(Integer.parseInt(s, 2));
	}
	
	
	/**
	 * 反序列化被php序列化的字符串
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String recommendIdsHandler(String content) throws Exception {
		//反序列化
	    PHPSerialize p = new PHPSerialize();
        PHPValue c = p.unserialize(content);
        String idStr = "";
        
        Collection<PHPValue> values = c.toHashMap().values();
        for (PHPValue value : values) {
        	
        	HashMap map = value.toHashMap();
        	String type = map.get("type").toString();
        	Integer id = Integer.parseInt( map.get("id").toString());
        	
        	//解决Id冲突
        	switch (type) {
			case "car":
				id = id + 200;
				
				break;
			case "free":
				if(id < 100){
					id = id + 300;
				}else{
					id = id - 8500;
				}
				break;
			case "travel":
				id = id + 100;
				break;
			case "line":
				break;
			default:
				break;
			}
        	
        	idStr += id;
        	idStr += ",";
		}
        
        if(idStr.length() >= 1){
        	//去除最后一个逗号
        	idStr = idStr.substring(0, idStr.length()-1);
        }
		
	    return idStr;
	}
	
}
