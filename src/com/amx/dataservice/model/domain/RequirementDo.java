package com.amx.dataservice.model.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="customorder")
public class RequirementDo {
	
    /**
     * @主键.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String destination;
	private String partner;
	@Column(name="go_off_time")
	private String goOffTime;
	private String remark;
	private String name;
	private Integer gender;
	private String phone;
	private String email;
	
	@Column(name="user_id")
	private Long userId;
	
	/**
	 * 定金
	 */
	private Double subscription;
	
	@Column(name="order_state_id")
	private Integer orderStateId;

    /**
     * @创建时间.
     */
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="create_time",nullable=false)
    private Date createTime; 
	

    /**
     * @更新时间.
     */
    @Temporal(TemporalType.TIMESTAMP) 
    @Column(name="modify_time")
    private Date modifyTime; 
	
    /**
     * @备注.
     */
	@Basic(fetch=FetchType.LAZY)
	@Column(name="sys_remark")
    private String sysRemark; 
	
    /**
     * @是否删除.
     */
    @Column(name="is_delete")
    private Boolean isDelete; 
	
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Double getSubscription() {
		return subscription;
	}
	public void setSubscription(Double subscription) {
		this.subscription = subscription;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getGoOffTime() {
		return goOffTime;
	}
	public void setGoOffTime(String goOffTime) {
		this.goOffTime = goOffTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getSysRemark() {
		return sysRemark;
	}
	public void setSysRemark(String sysRemark) {
		this.sysRemark = sysRemark;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getOrderStateId() {
		return orderStateId;
	}
	public void setOrderStateId(Integer orderStateId) {
		this.orderStateId = orderStateId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
