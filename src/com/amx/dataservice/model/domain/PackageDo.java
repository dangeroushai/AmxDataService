/*
 * 文件名：Product_PackageBo.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved.
 * 描述： Product_PackageBo.java
 * 修改人：leihaijun
 * 修改时间：Tue Mar 21 15:23:12 CST 2017
 * 修改内容：新增
 */
package com.amx.dataservice.model.domain;

import java.math.BigDecimal;
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
@Table(name="package")
public class PackageDo {

    /**
     * @主键.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

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
     * @人数梯度(成人).
     */
	@Column(name="adult_level")
    private String adultLevel; 

    /**
     * @权重.
     */
    private Integer weight; 

    /**
     * @备注.
     */
    private String remark; 

    /**
     * @待付价格梯度（儿童）.
     */
    @Column(name="ob_child_price_level")
    private String obChildPriceLevel; 

    /**
     * @人数上限.
     */
    @Column(name="max_person_num")
    private Integer maxPersonNum; 

    /**
     * @价格梯度（儿童）.
     */
    @Column(name="child_price_level")
    private String childPriceLevel; 

    /**
     * @计价方式（成人）.
     */
    @Column(name="adult_valuation_way")
    private Integer adultValuationWay; 

    /**
     * @待付价格梯度（成人）.
     */
    @Column(name="ob_adult_price_level")
    private String obAdultPriceLevel; 

    /**
     * @单人起始价.
     */
    @Column(name="start_price")
    private BigDecimal startPrice; 

    /**
     * @所属产品.
     */
    @Column(name="product_id")
    private Integer productId; 

    /**
     * @套餐名称.
     */
    @Column(name="en_name")
    private String enName; 

    /**
     * @时长.
     */
    private Float duration; 

    /**
     * @成人上限.
     */
    @Column(name="max_adult_num")
    private Integer maxAdultNum; 

    /**
     * @介绍.
     */
    private String description; 

    /**
     * @套餐名称.
     */
    private String name; 

    /**
     * @价格梯度（成人）.
     */
    @Column(name="adult_price_level")
    private String adultPriceLevel; 

    /**
     * @人数下限.
     */
    @Column(name="min_person_num")
    private Integer minPersonNum; 

    /**
     * @售卖状态.
     */
    @Column(name="sale_state")
    private Integer saleState; 

    /**
     * @计价方式（儿童）.
     */
    @Column(name="child_valuation_way")
    private Integer childValuationWay; 

    /**
     * @人数梯度(儿童).
     */
    @Column(name="child_level")
    private String childLevel; 

    /**
     * @儿童上限.
     */
    @Column(name="max_child_num")
    private Integer maxChildNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getAdultLevel() {
		return adultLevel;
	}

	public void setAdultLevel(String adultLevel) {
		this.adultLevel = adultLevel;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getObChildPriceLevel() {
		return obChildPriceLevel;
	}

	public void setObChildPriceLevel(String obChildPriceLevel) {
		this.obChildPriceLevel = obChildPriceLevel;
	}

	public Integer getMaxPersonNum() {
		return maxPersonNum;
	}

	public void setMaxPersonNum(Integer maxPersonNum) {
		this.maxPersonNum = maxPersonNum;
	}

	public String getChildPriceLevel() {
		return childPriceLevel;
	}

	public void setChildPriceLevel(String childPriceLevel) {
		this.childPriceLevel = childPriceLevel;
	}

	public Integer getAdultValuationWay() {
		return adultValuationWay;
	}

	public void setAdultValuationWay(Integer adultValuationWay) {
		this.adultValuationWay = adultValuationWay;
	}

	public String getObAdultPriceLevel() {
		return obAdultPriceLevel;
	}

	public void setObAdultPriceLevel(String obAdultPriceLevel) {
		this.obAdultPriceLevel = obAdultPriceLevel;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public Integer getMaxAdultNum() {
		return maxAdultNum;
	}

	public void setMaxAdultNum(Integer maxAdultNum) {
		this.maxAdultNum = maxAdultNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdultPriceLevel() {
		return adultPriceLevel;
	}

	public void setAdultPriceLevel(String adultPriceLevel) {
		this.adultPriceLevel = adultPriceLevel;
	}

	public Integer getMinPersonNum() {
		return minPersonNum;
	}

	public void setMinPersonNum(Integer minPersonNum) {
		this.minPersonNum = minPersonNum;
	}

	public Integer getSaleState() {
		return saleState;
	}

	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
	}

	public Integer getChildValuationWay() {
		return childValuationWay;
	}

	public void setChildValuationWay(Integer childValuationWay) {
		this.childValuationWay = childValuationWay;
	}

	public String getChildLevel() {
		return childLevel;
	}

	public void setChildLevel(String childLevel) {
		this.childLevel = childLevel;
	}

	public Integer getMaxChildNum() {
		return maxChildNum;
	}

	public void setMaxChildNum(Integer maxChildNum) {
		this.maxChildNum = maxChildNum;
	}     
}
