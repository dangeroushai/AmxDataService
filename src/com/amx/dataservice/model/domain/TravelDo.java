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

@Entity
@Table(name = "travel")
public class TravelDo {

	/**
	 * @是否删除.
	 */
	@Column(name = "is_delete")
	private Boolean isDelete;

	/**
	 * @主键.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * @创建时间.
	 */
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/**
	 * @更新时间.
	 */
	@Column(name = "modify_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;


	/**
	 * @名称.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * @天数.
	 */
	@Column(name = "day_amount")
	private Integer dayAmount;

	@Column(name = "user_id")
	private Long userId;


	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDayAmount() {
		return dayAmount;
	}

	public void setDayAmount(Integer dayAmount) {
		this.dayAmount = dayAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
