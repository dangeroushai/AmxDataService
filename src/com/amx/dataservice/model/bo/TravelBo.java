package com.amx.dataservice.model.bo;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.TravelDo;

public class TravelBo  extends BaseBo<TravelDo>{

	/**
	 * @主键.
	 */
	private Long id;

	/**
	 * @修改时间.
	 */
	private Date modifyTime;

	/**
	 * @名称.
	 */
	private String name;

	/**
	 * @天数.
	 */
	private Integer dayAmount;

	private Long userId;
	
	public TravelBo(){}
	
	public TravelBo(TravelDo tdo){
		init(tdo);	
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

	@Override
	void init(TravelDo obj) {
		BeanUtils.copyProperties(obj, this);
	}
}
