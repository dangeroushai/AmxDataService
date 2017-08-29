package com.amx.dataservice.model.bo;

import java.sql.Time;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.CartDo;

/**
 * 
 * @author DangerousHai
 *
 */
public class CartBo extends BaseBo <CartDo>{
	

   /**
    * @主键.
    */
   private Long id; 


   /**
    * @套餐ID.
    */
   private Long packageId; 

   /**
    * @出发日期.
    */
   private Date goOffDate; 

   /**
    * @用户ID.
    */
   private Long userId; 

   /**
    * @成人数.
    */
   private Integer adultNum; 

   /**
    * @儿童数.
    */
   private Integer childNum; 

   /**
    * @语言ID.
    */
   private Integer languageId; 

   /**
    * @产品ID.
    */
   private Long productId; 


   /**
    * @出发时间.
    */
   private Time goOffTime;

   public CartBo(){}
	
	/**
	 * 使用do 构造  bo
	 * @param pdo
	 */
	public CartBo(CartDo pdo){
		init(pdo);
	}
	
	@Override
	public void init(CartDo pdo){
		/*拷贝基本属性*/
		BeanUtils.copyProperties(pdo, this);
	}


	public Date getGoOffDate() {
		return goOffDate;
	}

	public void setGoOffDate(Date goOffDate) {
		this.goOffDate = goOffDate;
	}


	public Integer getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Time getGoOffTime() {
		return goOffTime;
	}

	public void setGoOffTime(Time goOffTime) {
		this.goOffTime = goOffTime;
	}
	
}
