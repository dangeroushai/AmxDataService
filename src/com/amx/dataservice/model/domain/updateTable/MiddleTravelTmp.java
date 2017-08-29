package com.amx.dataservice.model.domain.updateTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="middle_sline_travel_tmp")
public class MiddleTravelTmp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "day_order")
	private Integer dayOrder;
	
    private Long sdate;
    
    @Column(name = "item_order")
    private Integer itemOrder;
    
    @Column(name = "go_off_time")
    private String goOffTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getDayOrder() {
		return dayOrder;
	}

	public void setDayOrder(Integer dayOrder) {
		this.dayOrder = dayOrder;
	}

	public Long getSdate() {
		return sdate;
	}

	public void setSdate(Long sdate) {
		this.sdate = sdate;
	}

	public Integer getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
	}

	public String getGoOffTime() {
		return goOffTime;
	}

	public void setGoOffTime(String goOffTime) {
		this.goOffTime = goOffTime;
	}

}
