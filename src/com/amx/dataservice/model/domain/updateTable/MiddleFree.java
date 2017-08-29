package com.amx.dataservice.model.domain.updateTable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="middle_sline_free_product")
public class MiddleFree {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 


    private String attrid;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAttrid() {
		return attrid;
	}



	public void setAttrid(String attrid) {
		this.attrid = attrid;
	}


}
