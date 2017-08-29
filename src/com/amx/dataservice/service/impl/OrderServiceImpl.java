package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amx.dataservice.dao.OrderDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.OrderBo;
import com.amx.dataservice.model.domain.OrderDo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.model.qo.OrderQuery;
import com.amx.dataservice.service.CurrencyService;
import com.amx.dataservice.service.LanguageService;
import com.amx.dataservice.service.OrderService;
import com.amx.dataservice.service.PackageService;
import com.amx.dataservice.service.ProductService;
import com.amx.dataservice.service.TravellerService;
import com.amx.dataservice.util.BeanUtil;

@Service("orderService")
public class OrderServiceImpl extends BaseService implements OrderService {

	@Autowired
	private OrderDao dao;
	
	@Autowired
	private TravellerService travellerService;
	
	@Autowired
	private LanguageService languageService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private PackageService packageService;
	@Autowired
	private ProductService productService;

	@Override
	public long getProductIdById(long id) {
		return dao.findOne(id).getProductId();
	}
	
	@Override
	public OrderBo findOne(long id) {
		OrderBo bo = new OrderBo(dao.findOne(id));
		/*设置相关名称*/
		bo.setLanguageName(languageService.findOne(bo.getLanguageId()).getName());
		bo.setCurrencyName(currencyService.findOne(bo.getCurrencyId()).getName());
		bo.setPackageName(packageService.findOne(bo.getPackageId()).getName());
		bo.setProductName(productService.findOne(bo.getProductId()).getName());
		
		//查找出行人
		bo.setTravellerList(travellerService.findAllByOrderId(bo.getId()).getContent());
		
		return bo;
	}
	
	@Override
	public PageResponseDto<OrderBo> findAllByQuery(OrderQuery query) {
		List<OrderBo> boList = null;
		
		//按时间降序排列
		Sort sort = new Sort(Direction.DESC, "createTime");
		//分页
		PageRequest pageRequest = new PageRequest(query.getPageIndex(), query.getPageSize(), sort ) ;
		
		//取样探针
		OrderDo probe = new OrderDo();
		probe.setUserId(query.getUserId());
		probe.setOrderStateId(query.getStateId());
		probe.setIsDelete(false);
		
		Example<OrderDo> example = Example.of(probe);

		Page<OrderDo> page = dao.findAll(example , pageRequest);
		List<OrderDo> doList = page.getContent();
		
		//Do => Bo
		if(doList != null){
			boList = new ArrayList<OrderBo>();
			for(OrderDo ado : doList){
				OrderBo orderBo = new OrderBo(ado);
				
				boList.add(orderBo);
			}
		}
		
		return new PageResponseDto<OrderBo>(page.getTotalElements(), page.getTotalPages(), query.getPageIndex(), boList);
	}
	
	@Override
	@Transactional
	public UpdateResponseDto save(OrderBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		OrderDo entity = new OrderDo();
		/*关键属性*/
		BeanUtils.copyProperties(bo, entity);
		
		/*设置非空属性*/
		entity.setIsDelete(false);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		
		long orderId = dao.saveAndFlush(entity ).getId();
		if(orderId > 0 && travellerService.save(bo.getTravellerList(), orderId).getCode().equals(UpdateCodeEnum.SUCCESS.getCode())){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
			response.setMsg(orderId + "");
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}
	
	@Override
	public UpdateResponseDto update(OrderBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 

		OrderDo odo = dao.findOne(bo.getId());
		BeanUtil.copyNotNullProperties(bo, odo);
		odo.setModifyTime(new Date());
		
		if(dao.save(odo) != null){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}
	
	
	/**
	 * 订单-执行逻辑删除
	 */
	@Override
	@Transactional
	public UpdateResponseDto delete(long id) {
		UpdateResponseDto response = new UpdateResponseDto();
		
		if(dao.logicalDelete(id) > 0 && travellerService.deleteByOrderId(id).getCode().equals(UpdateCodeEnum.SUCCESS.getCode())){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}

	@Override
	public UpdateResponseDto saveInBatch(List<OrderBo> boList) {
		UpdateResponseDto response = new UpdateResponseDto();
		StringBuilder sb = new StringBuilder();
		for (OrderBo bo : boList) {
			UpdateResponseDto updateResponseDto = this.save(bo);
			if(updateResponseDto != null && updateResponseDto.getCode().equals(200)){
				sb.append(updateResponseDto.getMsg() + ",");
			}
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		response.setMsg(sb.toString());
		
		if(response.getMsg().split(",").length == boList.size()){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}

	@Override
	public UpdateResponseDto deleteInBatch(List<Long> idList) {
		UpdateResponseDto response = new UpdateResponseDto();
		for (Long id : idList) {
			this.delete(id);
		}		
		
		response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		
		return response;
	}

	@Override
	public List<OrderBo> findAllByTradeNo(String tradeNo) {
		List<OrderBo> boList = null;
		
		OrderDo probe = new OrderDo();
		probe.setTradeNo(tradeNo);
		Example<OrderDo> example = Example.of(probe );
		List<OrderDo> findAll = dao.findAll(example );
		if(findAll != null){
			boList = new ArrayList<OrderBo>();
			for (OrderDo orderDo : findAll) {
				boList.add(new OrderBo(orderDo));
			}
		}
		return boList;
	}

	@Override
	public List<OrderBo> findAllByIdList(List<Long> idList) {
		List<OrderBo> boList = null;
		List<OrderDo> doList = dao.findByIdIn(idList);
		if(doList != null){
			boList = new ArrayList<OrderBo>();
			for (OrderDo orderDo : doList) {
				boList.add(new OrderBo(orderDo));
			}
		}
		
		return boList;
	}

	@Override
	public UpdateResponseDto updateInBatch(List<OrderBo> boList) {
		UpdateResponseDto response = new UpdateResponseDto();
		
		ArrayList<OrderDo> entities = new ArrayList<OrderDo>();
		Date modifyTime = new Date();
		for (OrderBo orderBo : boList) {
			OrderDo findOne = dao.findOne(orderBo.getId());
			BeanUtil.copyNotNullProperties(orderBo, findOne);
			findOne.setModifyTime(modifyTime);
			
			entities.add(findOne);
		}
		if(dao.save(entities) != null){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}
		
		return response;
	}
}
