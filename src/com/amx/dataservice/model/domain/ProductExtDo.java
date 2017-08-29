package com.amx.dataservice.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "productExt")
public class ProductExtDo {
	
	/**
     * @主键.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * 关联产品
	 */
	@Column(name = "product_id", nullable=false, unique=true)
	private Long productId;
	
    /**
     * @车导产品（Car And Tour).
     */
	@Column(name = "cat_product_ids", length=500)
    private String catProductIds;
    
    /**
     * @酒店产品
     */
    @Column(name = "hotel_product_ids", length=500)
    private String hotelProductIds;
    
    /**
     * @是否包含机票
     */
    @Column(name = "is_ticket" ,nullable=false)
    private Boolean  isTicket;
    
    /**
     * @机票产品
     */
    @Column(name = "ticket_product_ids", length=500, nullable=true)
    private String ticketProductIds;
    
    /**
     * @周边产品
     */
    @Column(name = "addition_product_ids", length=500)
    private String additionProductIds; 
    
	@Column(name = "create_time" ,nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createTime;

	/**
	 * @更新时间.
	 */
	@Column(name = "modify_time" ,nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modifyTime;
	
	/**
	 * @是否删除.
	 */
	@Column(name = "is_delete" ,nullable=false ,columnDefinition="int default 0")
	private Boolean isDelete;

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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

	public String getCatProductIds() {
		return catProductIds;
	}

	public void setCatProductIds(String catProductIds) {
		this.catProductIds = catProductIds;
	}

	public String getHotelProductIds() {
		return hotelProductIds;
	}

	public void setHotelProductIds(String hotelProductIds) {
		this.hotelProductIds = hotelProductIds;
	}

	public Boolean getIsTicket() {
		return isTicket;
	}

	public void setIsTicket(Boolean isTicket) {
		this.isTicket = isTicket;
	}

	public String getTicketProductIds() {
		return ticketProductIds;
	}

	public void setTicketProductIds(String ticketProductIds) {
		this.ticketProductIds = ticketProductIds;
	}

	public String getAdditionProductIds() {
		return additionProductIds;
	}

	public void setAdditionProductIds(String additionProductIds) {
		this.additionProductIds = additionProductIds;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
