package com.amx.dataservice.model.bo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.LanguageDo;

public class LanguageBo extends BaseBo<LanguageDo>{
	


	/**
     * @主键.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id; 


    /**
     * @语言名称（中文）.
     */
    private String name;

    public LanguageBo(LanguageDo ldo){
    	init(ldo);
    }

	@Override
	void init(LanguageDo ldo) {
		/*拷贝基本属性*/
		BeanUtils.copyProperties(ldo, this);		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	} 

}
