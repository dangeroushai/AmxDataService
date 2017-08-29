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
@Table(name="customhodometer")
public class CustomHodometerDo {
	
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
     * @用户备注.
     */
    private String remark; 


    /**
     * @套餐ID.
     */
    @Column(name="package_id")
    private Long packageId; 

    /**
     * @出发日期.
     */
    @Column(name="go_off_date",nullable = true)
    private Date goOffDate; 


    /**
     * @用户ID.
     */
    @Column(name="user_id")
    private Long userId; 

    /**
     * @成人数.
     */
    @Column(name="adult_num")
    private Integer adultNum; 


    /**
     * @儿童数.
     */
    @Column(name="child_num")
    private Integer childNum; 


    /**
     * @语言ID.
     */
    @Column(name="language_id")
    private Integer languageId; 

    /**
     * @产品ID.
     */
    @Column(name="product_id")
    private Long productId; 

    /**
     * @所属行程ID.
     */
    @Column(name="travel_id")
    private Long travelId; 

    /**
     * @出发时间.
     */
    @Column(name="go_off_time",nullable = true)
    private Time goOffTime;


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


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Long getPackageId() {
		return packageId;
	}


	public Long getTravelId() {
		return travelId;
	}


	public void setTravelId(Long travelId) {
		this.travelId = travelId;
	}


	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}


	public Date getGoOffDate() {
		return goOffDate;
	}


	public void setGoOffDate(Date goOffDate) {
		this.goOffDate = goOffDate;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
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
