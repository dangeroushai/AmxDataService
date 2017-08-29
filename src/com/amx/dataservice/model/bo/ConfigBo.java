package com.amx.dataservice.model.bo;

import com.amx.dataservice.model.domain.ConfigDo;

public class ConfigBo {
	

	/**
	 * @主键.
	 */
	private Integer id;
	
	/**
	 * @名.
	 */
	private String name;
	

    /**
     * @key
     */
    private String key; 

    /**
     * value.
     */
    private String value;

	public ConfigBo(ConfigDo configDo) {
		if(configDo != null){
			this.id = configDo.getId();
			this.name = configDo.getName();
			this.key = configDo.getKey();
			this.value = configDo.getValue();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	} 

}
