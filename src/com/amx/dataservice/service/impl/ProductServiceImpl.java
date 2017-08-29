package com.amx.dataservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amx.dataservice.dao.ProductDao;
import com.amx.dataservice.enums.ProductTypeEnum;
import com.amx.dataservice.model.bo.ProductBo;
import com.amx.dataservice.model.domain.ProductDo;
import com.amx.dataservice.model.dto.PageResponseDto;
import com.amx.dataservice.model.qo.ProductQuery;
import com.amx.dataservice.service.HodometerService;
import com.amx.dataservice.service.PackageService;
import com.amx.dataservice.service.ProductService;
import com.amx.dataservice.util.StringUtil;

@Service("productService")
public class ProductServiceImpl extends BaseService implements ProductService {

	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	private ProductDao productDao;

	@Autowired
	private PackageService packageService;
	@Autowired
	private HodometerService hodometerService;

	/*
	 * @Autowired private ProductRepository productRepository;
	 */

	@Override
	@Transactional(readOnly = true)
	public ProductBo findOne(long id) {
		ProductBo productBo = null;
		
		ProductDo productDo = productDao.findOne(id);
		if(productDo != null){	
			productBo = new ProductBo(productDo);
			//套餐IDs
			productBo.setPackageIdList(packageService.getIdListByProductId(id));
			//行程IDs
			if(ProductTypeEnum.TRAVEL.getId() == productBo.getTypeId()){
				productBo.setHodometerIdList(hodometerService.getIdListByProductId(id));
			}
		}

		return productBo;
	}
	
	@Override
	public List<ProductBo> findAllByIdList(List<Long> ids){
		List<ProductBo> productBoList = null;
		
		List<ProductDo> productDoList = productDao.findByIdIn(ids);
		if(productDoList != null){
			productBoList = new ArrayList<ProductBo>(productDoList.size());
			for (ProductDo productDo : productDoList) {
				productBoList.add(new ProductBo(productDo));
			}
		}
		
		return productBoList;
	}

	@Override
	public PageResponseDto<ProductBo> findAllByQuery(final ProductQuery productQuery) {
		List<ProductBo> boList = null;
		
		//获取套餐在价格范围内的产品Id
		List<Integer> productIdList = packageService.getProductIdListByStartPriceBetween(productQuery.getStartPrice(), productQuery.getEndPrice());
		
		//String selectFields = "id,no,name,sub_title,type_id,introduce,start_city_ids,dest_ids,attr_ids,cover_pic";
		String selectFields = "*";
		/* 构造原生SQL语句  */ 
		StringBuilder sqlBuilder = new StringBuilder("SELECT " + selectFields   + " FROM product WHERE ");
		if(productQuery.getTypeId() != null && productQuery.getTypeId() != 0){
			sqlBuilder.append("type_id = " + productQuery.getTypeId() + " AND ");
		}else{
			//默认不查找自由行产品
			sqlBuilder.append("type_id <> " + ProductTypeEnum.FREE.getId() + " AND ");
		}
		if(!StringUtil.isEmpty(productQuery.getKeyword())){
			sqlBuilder.append("(name LIKE '%" +productQuery.getKeyword()+ "%' OR sub_title LIKE '%" +productQuery.getKeyword()+ "%') AND ");
		}
		if(productQuery.getDestinationId() != null && productQuery.getDestinationId() != 0){
			sqlBuilder.append("FIND_IN_SET(" + productQuery.getDestinationId() + ",dest_ids) AND ");
		}
		if(productQuery.getThemeId() != null && productQuery.getThemeId() != 0){
			sqlBuilder.append("FIND_IN_SET(" + productQuery.getThemeId() + ",attr_ids) AND ");
		}
		if(productQuery.getSceneId() != null && productQuery.getSceneId() != 0){
			sqlBuilder.append("FIND_IN_SET(" + productQuery.getSceneId() + ",attr_ids) AND ");
		}
		if((productQuery.getStartPrice() != null && productQuery.getStartPrice() != 0) || (productQuery.getEndPrice() != null && productQuery.getEndPrice() != 0)){
			String productIds = this.getProductIds(productIdList);
			if(StringUtil.isEmpty(productIds)){
				//无数据
				boList = new ArrayList<ProductBo>();
				return new PageResponseDto<ProductBo>(Long.valueOf(boList.size()), Integer.valueOf(0), Integer.valueOf(0), boList);
			}
			sqlBuilder.append("id IN (" + productIds  + ") AND ");
		}
		//固定查询条件
		sqlBuilder.append("is_delete = 0 AND sale_state = 1 ORDER BY ");
		//排序字段
		if(productQuery.isSortByAttr()){
			sqlBuilder.append("attr_weight,");
		}
		if(productQuery.isSortByDest()){
			sqlBuilder.append("dest_weight,");
		}
		if(productQuery.isSortByTheme()){
			sqlBuilder.append("theme_weight,");
		}
		//默认按时间(id)排序
		sqlBuilder.append("id DESC ");
		if(productQuery.getPageIndex() != null && productQuery.getPageSize() != null){
			sqlBuilder.append("LIMIT " + (productQuery.getPageIndex() * productQuery.getPageSize()) + ", " + productQuery.getPageSize());
		}
		String sql = sqlBuilder.toString();
		Query nativeQuery = entityManager.createNativeQuery(sql, ProductDo.class);
		@SuppressWarnings("unchecked")
		List<ProductDo> doList = nativeQuery.getResultList();
		//Do => Bo
		if(doList != null){
			boList = new ArrayList<ProductBo>();
			for(ProductDo hdo : doList){
				boList.add(new ProductBo(hdo));
			}
		}
		
		return new PageResponseDto<ProductBo>(Long.valueOf(boList.size()), Integer.valueOf(0), Integer.valueOf(0), boList);
//		return new PageResponseDto<ProductBo>(page.getTotalElements(), page.getTotalPages(), productQuery.getPageIndex(), boList);
	}
	
	@Override
	public List<Integer> getAllAttrIdsByDestId(Integer destId){
		List<ProductDo> productList = productDao.findAllByDestinationId(destId);
		List<Integer> attrIdList = new ArrayList<Integer>();
		for (ProductDo productDo : productList) {
			String attrIdsStr = productDo.getAttrIds();
			if(attrIdsStr != null){
				String[] attrIdsArr = attrIdsStr.split(",");
				for (int i = 0; i < attrIdsArr.length; i++) {
					Integer id = Integer.parseInt(attrIdsArr[i]);
					if(!attrIdList.contains(id)){
						attrIdList.add(id);
					}
				}
			}
		}
		
		return attrIdList;
	}
	

	/**
	 * 生成以逗号隔开的ID字符串
	 * @param productIdList
	 * @return
	 */
	private String getProductIds(List<Integer> productIdList) {
		
		StringBuilder sb = new StringBuilder("");
		
		if(productIdList != null && productIdList.size() != 0){
			//至少有一个元素才遍历
			for (Integer id : productIdList) {
				sb.append(id);
				sb.append(",");
			}
			//删除最后一个逗号
			sb.deleteCharAt(sb.length() - 1);
		}
		
		return sb.toString();
	}
	
	@Deprecated
	public PageResponseDto<ProductBo> abandon_findAllByQuery(final ProductQuery productQuery) {
/*			List<ProductBo> boList = null;
		
		//获取套餐在价格范围内的产品Id
		List<Integer> productIdList = packageService.getProductIdListByStartPriceBetween(productQuery.getStartPrice(), productQuery.getEndPrice());
		
		Specification<ProductDo> spec = new Specification<ProductDo>() {
			
			@Override
			public Predicate toPredicate(Root<ProductDo> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
			Path<Integer> typeId = root.get("typeId");
				Path<String> name = root.get("name");
				Path<String> subTitle = root.get("subTitle");
				Path<Integer> pre = root.get("presaleDelay");
				Path<List<Integer>> destIds = root.get("destIds");
				Path<List<Integer>> attrIds = root.get("attrIds");
				
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				//关键字
				if(productQuery.getKeyword() != null){
					String keywordPartten = "%"+ productQuery.getKeyword() +"%";
					predicateList.add(cb.or(cb.like(name, keywordPartten) , cb.like(subTitle, keywordPartten)));
				}
				//产品类型
				if(productQuery.getTypeId() != null){
					predicateList.add( cb.equal(typeId,productQuery.getTypeId()));
				}
				//价格
				if(productQuery.getStartPrice() != null || productQuery.getEndPrice() != null){
					In<Integer> id_in = cb.in(root.get("id").as(Integer.class));
					for (Integer productId : productIdList) {
						id_in.value(productId); 
					}
					predicateList.add(id_in);
				}
				
				cb.function("FIND_IN_SET", Boolean.class, cb.parameter(Integer.class, "theme_id") ,destIds);
				
				
				TypedQuery<ProductDo> typeQuery = (TypedQuery<ProductDo>) em.createQuery(query);
				typeQuery.setParameter("theme_id", productQuery.getThemeId());

				
				//组合所有查询条件
				Predicate[] predicateArr = new Predicate[predicateList.size()];
				Predicate predicate = cb.and(predicateList.toArray(predicateArr));
				//把Predicate应用到CriteriaQuery中去,因为还可以给CriteriaQuery添加其他的功能，比如排序、分组啥的 
				query.where(predicate);
				
				
				//query.orderBy(o);
				
				//设定select字段
				query.multiselect(
						name,
						subTitle,
						pre//,
						//root.get("").as(String.class)
						//TODO
						);
				
		        return query.getRestriction();  
			}
		};
		
		//按时间降序排列
		Sort sort = new Sort(Direction.DESC, "createTime");
		//分页
		PageRequest pageRequest = new PageRequest(productQuery.getPageIndex(), productQuery.getPageSize(), sort ) ;
		
		Page<ProductDo> page = productDao.findAll(spec , pageRequest);
		List<ProductDo> doList = page.getContent();*/
				
				
/*
		//Get criteria builder
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();		
		CriteriaQuery<ProductDo> query = cb.createQuery(ProductDo.class);		
		Root<ProductDo> root = query.from(ProductDo.class);
		
		
		Path<Integer> typeId = root.get("typeId");
		Path<String> name = root.get("name");
		Path<String> subTitle = root.get("subTitle");
		Path<Integer> pre = root.get("presaleDelay");
		Path<List<Integer>> destIds = root.get("destIds");
		Path<List<Integer>> attrIds = root.get("attrIds");
		
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		//关键字
		if(productQuery.getKeyword() != null){
			String keywordPartten = "%"+ productQuery.getKeyword() +"%";
			predicateList.add(cb.or(cb.like(name, keywordPartten) , cb.like(subTitle, keywordPartten)));
		}
		//产品类型
		if(productQuery.getTypeId() != null){
			predicateList.add( cb.equal(typeId,productQuery.getTypeId()));
		}
		//价格
		if(productQuery.getStartPrice() != null || productQuery.getEndPrice() != null){
			In<Integer> id_in = cb.in(root.get("id").as(Integer.class));
			for (Integer productId : productIdList) {
				id_in.value(productId); 
			}
			predicateList.add(id_in);
		}
		TypedQuery<ProductDo> typedQuery = (TypedQuery<ProductDo>) entityManager.createQuery(query);
		//主题
		if(productQuery.getThemeId() != null){
			Expression<Boolean> function = cb.function("FIND_IN_SET", Boolean.class, cb.parameter(Integer.class, "theme_id") ,destIds);
			predicateList.add( cb.and(function , function));
			//设置参数值
			typedQuery.setParameter("theme_id", productQuery.getThemeId());
		}
		
		//组合所有查询条件
		Predicate[] predicateArr = new Predicate[predicateList.size()];
		Predicate predicate = cb.and(predicateList.toArray(predicateArr));
		//把Predicate应用到CriteriaQuery中去,因为还可以给CriteriaQuery添加其他的功能，比如排序、分组啥的 
		query.where(predicate);
		
		//query.orderBy(o);
		
		//设定select字段
		query.multiselect(
				name,
				subTitle,
				pre//,
				//root.get("").as(String.class)
				//TODO
				);
		*/
		
		return null;
	}
}


