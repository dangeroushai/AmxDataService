package com.amx.dataservice.model.bo;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.ProductExtDo;
import com.amx.dataservice.util.StringUtil;

/**
 * 产品扩展业务对象，所有关联对象都只指明ID
 * @author DangerousHai
 *
 */
public class ProductExtBo extends BaseBo <ProductExtDo>{
	

	/**
     * @主键.
     */
	private Long id;
	
	/**
	 * 关联产品
	 */
	private Long productId;
	
    /**
     * @车导产品（Car And Tour).
     */
    private List<Long> catProductIdList;
    
    /**
     * @酒店产品
     */
    private List<Long> hotelProductIdList;
    
    /**
     * @是否包含机票
     */
    private Boolean  isTicket;
    
    /**
     * @机票产品
     */
    private List<Long> ticketProductIdList;
    
    /**
     * @周边产品
     */
    private List<Long> additionProductIdList; 
    
	/**
	 * 使用do 构造  bo
	 * @param pdo
	 */
	public ProductExtBo(ProductExtDo pdo){
		init(pdo);
	}
	
	@Override
	public void init(ProductExtDo pdo){
		/*拷贝基本属性*/
		BeanUtils.copyProperties(pdo, this);
		/*处理多值属性*/
		this.catProductIdList = StringUtil.explodeIdStr2LongIdList(pdo.getCatProductIds());
		this.hotelProductIdList = StringUtil.explodeIdStr2LongIdList(pdo.getHotelProductIds());
		if(this.isTicket){
			this.ticketProductIdList = StringUtil.explodeIdStr2LongIdList(pdo.getTicketProductIds());
		}
		this.hotelProductIdList = StringUtil.explodeIdStr2LongIdList(pdo.getHotelProductIds());
		this.additionProductIdList = StringUtil.explodeIdStr2LongIdList(pdo.getAdditionProductIds());
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<Long> getCatProductIdList() {
		return catProductIdList;
	}

	public void setCatProductIdList(List<Long> catProductIdList) {
		this.catProductIdList = catProductIdList;
	}

	public List<Long> getHotelProductIdList() {
		return hotelProductIdList;
	}

	public void setHotelProductIdList(List<Long> hotelProductIdList) {
		this.hotelProductIdList = hotelProductIdList;
	}

	public Boolean getIsTicket() {
		return isTicket;
	}

	public void setIsTicket(Boolean isTicket) {
		this.isTicket = isTicket;
	}

	public List<Long> getTicketProductIdList() {
		return ticketProductIdList;
	}

	public void setTicketProductIdList(List<Long> ticketProductIdList) {
		this.ticketProductIdList = ticketProductIdList;
	}

	public List<Long> getAdditionProductIdList() {
		return additionProductIdList;
	}

	public void setAdditionProductIdList(List<Long> additionProductIdList) {
		this.additionProductIdList = additionProductIdList;
	}
}
