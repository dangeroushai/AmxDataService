package com.amx.dataservice.thrift.impl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amx.dataservice.holder.SpringContextHolder;
import com.amx.dataservice.thrift.CommonThriftService;
import com.amx.dataservice.thrift.Request;
import com.amx.dataservice.thrift.Response;
import com.amx.dataservice.util.JsonUtil;
import com.fasterxml.jackson.databind.JavaType;

/**
 * Thrift 公共服务处理器类
 * 所有外部调用入口
 * @author DangerousHai
 *
 */
@Component("commonThriftService")
public class CommonThriftServiceImpl implements CommonThriftService.Iface{
	
	private Logger logger = LoggerFactory.getLogger(CommonThriftServiceImpl.class);
	
	private static final String PACKAGE_PATH = "com.amx.dataservice.service."; 
	private static final String SERVICE_SUFFIX = "Service"; 
	/**
	 * 缓存目标服务Bean&方法信息
	 */
	private static Map<String, ServiceMethodWrapper> sm_serviceMethod_cache_map = new HashMap<String, ServiceMethodWrapper>();
	
	@Override
	public Response invoke(Request request){
		Response response = new Response();
		
		String serviceName = PACKAGE_PATH + request.getServiceName() + SERVICE_SUFFIX;
		String methodName = request.getMethodName();
		String json_data = request.getData();
		
		try {
			//目标服务类 类类型
			Class<?> target_ServiceClass = null;
			//目标Bean
			Object target_Service = null;
			//目标方法
			Method target_method = null;
			//目标方法参数的类类型
			Class<? extends Object> paramsClass[] = null;
			
			Object result = null;
			
			//eg：Product_findAll
			String key = request.getServiceName() + "_" + request.getMethodName(); 
			
			//读缓存
			ServiceMethodWrapper serviceMethod = sm_serviceMethod_cache_map.get(key);
			if(serviceMethod != null && serviceMethod.isValid()){
				target_Service = serviceMethod.getTargetServiceBean();
				target_method = serviceMethod.getTargetMethod();
				paramsClass = serviceMethod.getTargetParamsClass();
			}else{//反射读取类和方法信息
				//查找Service类
				target_ServiceClass = Class.forName(serviceName);
				
				//查找Service Bean
				target_Service = SpringContextHolder.getBean(target_ServiceClass);
				
				if(target_Service == null){
					throw new RuntimeException("Service Bean Not Found");
				}
				
				Method[] methods = target_ServiceClass.getMethods();
				//查找Service方法
				for (Method method : methods) {
					if(method.getName().equalsIgnoreCase(methodName)){
						target_method = method;
						break;
					}
				}
				
				if(target_method == null){
					throw new RuntimeException("Method Not Found");
				}
				
				paramsClass = target_method.getParameterTypes();
				
				//将反射读取类和方法信息存入缓存
				serviceMethod =  new ServiceMethodWrapper(target_Service,target_method,paramsClass);
				sm_serviceMethod_cache_map.put(key, serviceMethod);
			}
			
			//如果参数个数大于1
			if(paramsClass.length > 1){
				throw new RuntimeException("The Number Of Parameter Must Be Less Than One");
			}	
			
			//目标方法有参数（最多一个参数）
			if(paramsClass.length == 1){
				Class<? extends Object> onlyParamClass = paramsClass[0];
				//唯一参数值
				Object onlyParam = null;
				
				//如果参数是java.util.List或其子类
				if(java.util.List.class.isAssignableFrom(onlyParamClass)){
					//读缓存
					//泛型集合参数类类型
					JavaType genericCollectionType = serviceMethod.getGenericCollectionType();
					//无缓存则通过反射获取
					if(genericCollectionType == null){
						//Type[] genericParameterTypes = target_method.getGenericParameterTypes();
						//Type onlyGenericParamType = genericParameterTypes[0];
						
						Type onlyGenericParamType = target_method.getParameters()[0].getParameterizedType();
						//如果不是泛型参数类型
						if(!(onlyGenericParamType instanceof ParameterizedType)){
							throw new RuntimeException("Gegeric Parameter Must Be java.util.List");
						}	
						ParameterizedType pt = (ParameterizedType) onlyGenericParamType;
						//具体泛型参数类型
						Class onlyGenericParamClass = (Class)pt.getActualTypeArguments()[0];
						genericCollectionType = JsonUtil.getCollectionType(ArrayList.class, onlyGenericParamClass);
						
						serviceMethod.setGenericCollectionType(genericCollectionType);
					}
					
					onlyParam = JsonUtil.mapper.readValue(json_data, genericCollectionType);
				}else{//非集合参数
					onlyParam = JsonUtil.mapper.readValue(json_data, onlyParamClass);
				}
				result = target_method.invoke(target_Service, onlyParam);
			}else{//目标方法无参数
				result = target_method.invoke(target_Service);
			}
		
			if(result == null){
				throw new RuntimeException("Null Value Returned");
			}
			
			response.setData(JsonUtil.mapper.writeValueAsString(result));
			response.setState(true);
		} catch (Exception e) {
			response.setState(false);
			response.setMsg(e.getMessage());
			
			//记录异常日志
			StringBuilder sb = new StringBuilder();
			sb.append("\n");
			sb.append("Target_ServiceName:").append(serviceName);
			sb.append("\t");
			sb.append("Target_MethodName:").append(methodName);
			sb.append("\n");
			sb.append("With_Data:").append(json_data);
					
			logger.error(sb.toString(), e);
		}
		
		return response;
	}
	
/*
	@Autowired
	private ProductService productService;
	@Autowired
	private PackageService packageService;
	@Autowired
	private HodometerService hodometerService;
	@Autowired
	private LanguageService languageService;
	@Autowired
	private AttributeService attributeService;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private CartService cartService;
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private UserService userService;
	@Autowired
	private TravelService travelService;
	@Autowired
	private CustomHodometerService customHodometerService;
	@Autowired
	private RequirementService requirementService;
	@Autowired
	private ConfigService configService;
	
	@Deprecated
	public Response invoke(Request request, Object ignoreThis){
		String service = request.getServiceName();
		String method = request.getMethodName();
		String data = request.getData();
		try {
			Response response = dispatch(service, method, data);
			if(response != null){
				return response;
			}
			throw new RuntimeException("The DataService Method Return A null Value");
		} catch (Exception e) {
			//FIXME - delete
			e.printStackTrace();
			//记录异常日志
			StringBuilder sb = new StringBuilder();
			sb.append("\n");
			sb.append("Target_ServiceName:").append(service);
			sb.append("\t");
			sb.append("Target_MethodName:").append(method);
			sb.append("\n");
			sb.append("With_Data:").append(data);
					
			logger.error(sb.toString(), e);
		}
		
		return new Response(false, "An Exception Occured In AmxDataService", null);
	}
	
	*//**
	 * 处理分发
	 * @param service 服务名
	 * @param method 方法名
	 * @param json_data jSON参数
	 * @return
	 *//*
	private Response dispatch(String service, String method, String json_data) throws Exception{
		Response response = new Response();
		
		Boolean responseSate = false;
		Object responseData = null;
		String responseMsg = null;
		
		switch (service.toUpperCase()) {
		case "PRODUCT":
			responseData = productDispatch(method, json_data);
			break;
		case "PACKAGE":
			responseData = packageDispatch(method, json_data);
			break;
		case "HODOMETER":
			responseData = hodometerDispatch(method, json_data);
			break;
		case "LANGUAGE":
			responseData = languageDispatch(method, json_data);
			break;
		case "ATTRIBUTE":
			responseData = attributeDispatch(method, json_data);
			break;
		case "ADVERTISEMENT":
			responseData = advertisementDispatch(method, json_data);
			break;
		case "REGION":
			responseData = regionDispatch(method, json_data);
			break;
		case "ARTICLE":
			responseData = articleDispatch(method, json_data);
			break;
		case "CURRENCY":
			responseData = currencyDispatch(method, json_data);
			break;
		case "CART":
			responseData = cartDispatch(method, json_data);
			break;
		case "FAVORITE":
			responseData = favoriteDispatch(method, json_data);
			break;
		case "COMMENT":
			responseData = commentDispatch(method, json_data);
			break;
		case "ORDER":
			responseData = orderDispatch(method, json_data);
			break;
		case "CONTACT":
			responseData = contactDispatch(method, json_data);
			break;
		case "USER":
			responseData = userDispatch(method, json_data);
			break;
		case "TRAVEL":
			responseData = travelDispatch(method, json_data);
			break;
		case "CUSTOMHODOMETER":
			responseData = customHodometerDispatch(method, json_data);
			break;
		case "REQUIREMENT":
			responseData = requirementDispatch(method, json_data);
			break;
		case "CONFIG":
			responseData = configDispatch(method, json_data);
			break;
			
		default:
			responseMsg = "UNKNOW SERVICE NAME[" + service + "]";
			break;
		}
		
		try {
			response.setData(JsonUtil.mapper.writeValueAsString(responseData));
			if(responseData != null){
				responseSate = true;
			}
		} catch (JsonProcessingException e) {
//			responseMsg = "CAN NOT CONVERT RESULT TO JSON";
			throw new RuntimeException(e);
		}
		response.setState(responseSate);
		response.setMsg(responseMsg);
		
		return response;
	}

	private Object productDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findOne":
			try {
				Integer id = JsonUtil.mapper.readValue(json_data, Integer.class);
				responseData = productService.findOne(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByQuery":
			try {
				ProductQuery query = JsonUtil.mapper.readValue(json_data, ProductQuery.class);
				responseData = productService.findAllByQuery(query);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "getAllAttrIdsByDestId":
			try {
				Integer destId = JsonUtil.mapper.readValue(json_data, Integer.class);
				responseData = productService.getAllAttrIdsByDestId(destId);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	
	private Object packageDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findAllByProductId":
			try {
				Integer pid = JsonUtil.mapper.readValue(json_data, Integer.class);
				responseData = packageService.findAllByProductId(pid);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findOne":
			try {
				Long pid = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = packageService.findOne(pid);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	
	private Object hodometerDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findAllByProductId":
			try {
				Integer pid = JsonUtil.mapper.readValue(json_data, Integer.class);
				responseData = hodometerService.findAllByProductId(pid);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	
	private Object languageDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findAll":
			try {
				JavaType javaType = JsonUtil.getCollectionType(ArrayList.class, Integer.class);
				List<Integer> idList = JsonUtil.mapper.readValue(json_data, javaType);
				responseData = languageService.findAllByIdList(idList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findOne":
			try {
				Integer id = JsonUtil.mapper.readValue(json_data, Integer.class);
				responseData = languageService.findOne(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}	
	private Object attributeDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findOne":
			try {
				Integer id = JsonUtil.mapper.readValue(json_data, Integer.class);
				responseData = attributeService.findOne(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAll":
			try {
				JavaType javaType = JsonUtil.getCollectionType(ArrayList.class, Integer.class);
				List<Integer> idList = JsonUtil.mapper.readValue(json_data, javaType);
				responseData = attributeService.findAllByIdList(idList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByTypeId":
			try {
				Integer typeId = JsonUtil.mapper.readValue(json_data,  Integer.class);
				responseData = attributeService.findAllByTypeId(typeId);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}	
	private Object advertisementDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findAllByQuery":
			try {
				AdvertisementQuery query = JsonUtil.mapper.readValue(json_data, AdvertisementQuery.class);
				responseData = advertisementService.findAllByQuery(query);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}	
	
	private Object regionDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findOne":
			try {
				Integer id = JsonUtil.mapper.readValue(json_data, Integer.class);
				responseData = regionService.findOne(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAll":
			try {
				List<Integer> idList = JsonUtil.mapper.readValue(json_data, JsonUtil.getCollectionType(ArrayList.class, Integer.class));
				responseData = regionService.findAll(idList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	
	private Object articleDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findOne":
			try {
				Integer id = JsonUtil.mapper.readValue(json_data, Integer.class);
				responseData = articleService.findOne(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByQuery":
			try {
				ArticleQuery query = JsonUtil.mapper.readValue(json_data, ArticleQuery.class);
				responseData = articleService.findAllByQuery(query);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	
	private Object currencyDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findOne":
			try {
				Integer id = JsonUtil.mapper.readValue(json_data, Integer.class);
				responseData = currencyService.findOne(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	private Object cartDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findOne":
			try {
				Long id = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = cartService.findOne(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByQuery":
			try {
				PageQuery query = JsonUtil.mapper.readValue(json_data, PageQuery.class);
				responseData = cartService.findAllByQuery(query);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "save":
			try {
				CartBo bo = JsonUtil.mapper.readValue(json_data, CartBo.class);
				responseData = cartService.save(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "update":
			try {
				CartBo bo = JsonUtil.mapper.readValue(json_data, CartBo.class);
				responseData = cartService.update(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "delete":
			try {
				Long id = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = cartService.delete(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "deleteInBatch":
			try {
				JavaType javaType = JsonUtil.getCollectionType(ArrayList.class, CartBo.class);
				List<CartBo> idList = JsonUtil.mapper.readValue(json_data, javaType);
				responseData = cartService.deleteInBatch(idList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "count":
			try {
				CartBo bo = JsonUtil.mapper.readValue(json_data, CartBo.class);
				responseData = cartService.count(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	
	private Object favoriteDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "getProductIdListByUserId":
			try {
				Long userId = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = favoriteService.getProductIdListByUserId(userId);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByQuery":
			try {
				PageQuery query = JsonUtil.mapper.readValue(json_data, PageQuery.class);
				responseData = favoriteService.findAllByQuery(query);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "save":
			try {
				FavoriteBo bo = JsonUtil.mapper.readValue(json_data, FavoriteBo.class);
				responseData = favoriteService.save(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "delete":
			try {
				FavoriteBo bo = JsonUtil.mapper.readValue(json_data, FavoriteBo.class);
				responseData = favoriteService.delete(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	
	private Object commentDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findAllByQuery":
			try {
				PageQuery query = JsonUtil.mapper.readValue(json_data, PageQuery.class);
				responseData = commentService.findAllByQuery(query);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "save":
			try {
				CommentBo bo = JsonUtil.mapper.readValue(json_data, CommentBo.class);
				responseData = commentService.save(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	private Object orderDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "getProductIdById":
			try {
				long id = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = orderService.getProductIdById(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findOne":
			try {
				long id = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = orderService.findOne(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByQuery":
			try {
				OrderQuery query = JsonUtil.mapper.readValue(json_data, OrderQuery.class);
				responseData = orderService.findAllByQuery(query);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByIdList":
			try {
				JavaType javaType = JsonUtil.getCollectionType(ArrayList.class, Long.class);
				List<Long> idList = JsonUtil.mapper.readValue(json_data,javaType);
				responseData = orderService.findAllByIdList(idList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByTradeNo":
			try {
				String tradeNo = JsonUtil.mapper.readValue(json_data, String.class);
				responseData = orderService.findAllByTradeNo(tradeNo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "save":
			try {
				OrderBo bo = JsonUtil.mapper.readValue(json_data, OrderBo.class);
				responseData = orderService.save(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "saveInBatch":
			try {
				List<OrderBo> boList = JsonUtil.mapper.readValue(json_data,JsonUtil.getCollectionType(ArrayList.class, OrderBo.class));
				responseData = orderService.saveInBatch(boList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "updateInBatch":
			try {
				List<OrderBo> boList = JsonUtil.mapper.readValue(json_data,JsonUtil.getCollectionType(ArrayList.class, OrderBo.class));
				responseData = orderService.updateInBatch(boList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "update":
			try {
				OrderBo bo = JsonUtil.mapper.readValue(json_data, OrderBo.class);
				responseData = orderService.update(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "delete":
			try {
				Long bo = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = orderService.delete(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "deleteInBatch":
			try {
				List<Long> idList = JsonUtil.mapper.readValue(json_data, JsonUtil.getCollectionType(ArrayList.class, Long.class));
				responseData = orderService.deleteInBatch(idList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	
	private Object contactDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findOne":
			try {
				Long id = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = contactService.findOne(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByQuery":
			try {
				PageQuery query = JsonUtil.mapper.readValue(json_data, PageQuery.class);
				responseData = contactService.findAllByQuery(query);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "save":
			try {
				ContactBo bo = JsonUtil.mapper.readValue(json_data, ContactBo.class);
				responseData = contactService.save(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "delete":
			try {
				Long id = JsonUtil.mapper.readValue(json_data,Long.class);
				responseData = contactService.delete(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "deleteInBatch":
			try {
				JavaType javaType = JsonUtil.getCollectionType(ArrayList.class, ContactBo.class);
				List<ContactBo> boList = JsonUtil.mapper.readValue(json_data, javaType);
				responseData = contactService.deleteInBatch(boList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "count":
			try {
				ContactBo bo = JsonUtil.mapper.readValue(json_data, ContactBo.class);
				responseData = contactService.count(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	
	private Object userDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findOne":
			try {
				long id = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = userService.findOne(id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findOneByExample":
			try {
				UserBo example = JsonUtil.mapper.readValue(json_data, UserBo.class);
				responseData = userService.findOneByExample(example);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "save":
			try {
				UserBo bo = JsonUtil.mapper.readValue(json_data, UserBo.class);
				responseData = userService.save(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "update":
			try {
				UserBo bo = JsonUtil.mapper.readValue(json_data, UserBo.class);
				responseData = userService.update(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	private Object travelDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findOne":
			try {
				Long travelId = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = travelService.findOne(travelId);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByQuery":
			try {
				PageQuery query = JsonUtil.mapper.readValue(json_data, PageQuery.class);
				responseData = travelService.findAllByQuery(query);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "save":
			try {
				TravelBo bo = JsonUtil.mapper.readValue(json_data, TravelBo.class);
				responseData = travelService.save(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "update":
			try {
				TravelBo bo = JsonUtil.mapper.readValue(json_data, TravelBo.class);
				responseData = travelService.update(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "delete":
			try {
				Long id  = JsonUtil.mapper.readValue(json_data,  Long.class);
				responseData = travelService.delete(id );
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	private Object customHodometerDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findOneByTravelId":
			try {
				Long travelId = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = customHodometerService.findOneByTravelId(travelId);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByTravelId":
			try {
				Long travelId = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = customHodometerService.findAllByTravelId(travelId);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "findAllByUserId":
			try {
				Long userId = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = customHodometerService.findAllByUserId(userId);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "save":
			try {
				CustomHodometerBo bo = JsonUtil.mapper.readValue(json_data, CustomHodometerBo.class);
				responseData = customHodometerService.save(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "saveInBatch":
			try {
				List<CustomHodometerBo> boList = JsonUtil.mapper.readValue(json_data, JsonUtil.getCollectionType(ArrayList.class,CustomHodometerBo.class));
				responseData = customHodometerService.save(boList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "update":
			try {
				CustomHodometerBo bo = JsonUtil.mapper.readValue(json_data, CustomHodometerBo.class);
				responseData = customHodometerService.update(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "updateInBatch":
			try {
				List<CustomHodometerBo> boList = JsonUtil.mapper.readValue(json_data, JsonUtil.getCollectionType(ArrayList.class,CustomHodometerBo.class));
				responseData = customHodometerService.update(boList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		case "delete":
			try {
				List<Long> idList  = JsonUtil.mapper.readValue(json_data, JsonUtil.getCollectionType(ArrayList.class, Long.class));
				responseData = customHodometerService.delete(idList );
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		case "deleteByTravelId":
			try {
				Long id  = JsonUtil.mapper.readValue(json_data, Long.class);
				responseData = customHodometerService.deleteByTravelId(id );
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	private Object requirementDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "save":
			try {
				RequirementBo bo = JsonUtil.mapper.readValue(json_data, RequirementBo.class);
				responseData = requirementService.save(bo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			break;
		}
		return responseData;
	}
	private Object configDispatch(String method, String json_data) {
		Object responseData = null;
		
		switch (method) {
		case "findAll":
			try {
				responseData = configService.findAll();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		default:
			break;
		}
		return responseData;
	}*/
}

/**
 * 封装反射获取的Bean和方法信息
 * @author DangerousHai
 *
 */
class ServiceMethodWrapper{
	private Object targetServiceBean;
	private Method targetMethod;
	private Class<? extends Object> targetParamsClass[];
	/**
	 * 泛型参数类型（为空表示目标方法的参数未Plain类型）
	 */
	private JavaType genericCollectionType;
	
	public ServiceMethodWrapper(Object target_Service, Method target_method, Class<? extends Object>[] paramsClass) {
		this.targetServiceBean = target_Service;
		this.targetMethod = target_method;
		this.targetParamsClass = paramsClass;
	}
	
	/**
	 * 有效性检查
	 * @return
	 */
	public boolean isValid(){
		if(this.targetServiceBean != null && this.targetMethod != null && this.targetParamsClass != null){
			return true;
		}
		return false;
	}
	
	public Object getTargetServiceBean() {
		return targetServiceBean;
	}
	public void setTargetServiceBean(Object targetServiceBean) {
		this.targetServiceBean = targetServiceBean;
	}
	public Method getTargetMethod() {
		return targetMethod;
	}
	public void setTargetMethod(Method targetMethod) {
		this.targetMethod = targetMethod;
	}
	public Class<? extends Object>[] getTargetParamsClass() {
		return targetParamsClass;
	}
	public void setTargetParamsClass(Class<? extends Object>[] targetParamsClass) {
		this.targetParamsClass = targetParamsClass;
	}

	public JavaType getGenericCollectionType() {
		return genericCollectionType;
	}

	public void setGenericCollectionType(JavaType genericCollectionType) {
		this.genericCollectionType = genericCollectionType;
	}
}
