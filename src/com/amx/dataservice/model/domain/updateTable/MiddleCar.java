package com.amx.dataservice.model.domain.updateTable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="middle_sline_car_product")
public class MiddleCar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 


    private String attrid;
    
    private String related;
    
    private String saleRule;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAttrid() {
		return attrid;
	}


	public String getSaleRule() {
		return saleRule;
	}


	public void setSaleRule(String saleRule) {
		this.saleRule = saleRule;
	}


	public void setAttrid(String attrid) {
		this.attrid = attrid;
	}


	public String getRelated() {
		return related;
	}


	public void setRelated(String related) {
		this.related = related;
	}
}
