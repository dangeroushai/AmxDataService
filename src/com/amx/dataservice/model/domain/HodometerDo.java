/*
 * 文件名：Product_HodometerBo.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved.
 * 描述： Product_HodometerBo.java
 * 修改人：leihaijun
 * 修改时间：Tue Mar 21 15:23:12 CST 2017
 * 修改内容：新增
 */
package com.amx.dataservice.model.domain;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="hodometer")
public class HodometerDo {
	
    /**
     * @主键.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 

    /**
     * @更新时间.
     */
    @Column(name="modify_time")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date modifyTime;
    
    /**
     * @创建时间.
     */
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP) 
    private Date createTime; 
	
    /**
     * @是否删除.
     */
	@Column(name="is_delete")
    private Boolean isDelete; 

    /**
     * @天序号.
     */
	@Column(name="day_order")
    private Integer dayOrder; 

    /**
     * @出发时间.
     */
	@Column(name="go_off_time")
    private Time goOffTime; 

    /**
     * @项目产品ID.
     */
	@Column(name="item_product_id")
    private Integer itemProductId; 

    /**
     * @备注.
     */
    private String remark; 

    /**
     * @项目产品套餐ID.
     */
    @Column(name="item_package_id")
    private Integer itemPackageId; 

    /**
     * @项目序号.
     */
    @Column(name="item_order")
    private Integer itemOrder; 

    /**
     * @所属产品.
     */
    @Column(name="product_id")
    private Long productId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getDayOrder() {
		return dayOrder;
	}

	public void setDayOrder(Integer dayOrder) {
		this.dayOrder = dayOrder;
	}

	public Time getGoOffTime() {
		return goOffTime;
	}

	public void setGoOffTime(Time goOffTime) {
		this.goOffTime = goOffTime;
	}

	public Integer getItemProductId() {
		return itemProductId;
	}

	public void setItemProductId(Integer itemProductId) {
		this.itemProductId = itemProductId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getItemPackageId() {
		return itemPackageId;
	}

	public void setItemPackageId(Integer itemPackageId) {
		this.itemPackageId = itemPackageId;
	}

	public Integer getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	} 
}
