package com.amx.dataservice.model.bo;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.FavoriteDo;

/**
 * 
 * @author DangerousHai
 *
 */
public class FavoriteBo extends BaseBo <FavoriteDo>{
	

   /**
    * @主键.
    */
   private Long id; 


   /**
    * @用户ID.
    */
   private Long userId; 

   /**
    * @产品ID.
    */
   private Long productId; 



   public FavoriteBo(){}
	
	/**
	 * 使用do 构造  bo
	 * @param pdo
	 */
	public FavoriteBo(FavoriteDo pdo){
		init(pdo);
	}
	
	@Override
	public void init(FavoriteDo pdo){
		/*拷贝基本属性*/
		BeanUtils.copyProperties(pdo, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
