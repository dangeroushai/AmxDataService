package com.amx.dataservice.enums;

/**
 * 计价方式
 * @author DangerousHai
 *
 */
public enum ValuationWayEnum {
	
	SAME_AS_ADULT("按成人（儿童按成人计费）",0),PRE_PERSON("按人数",1),LEVEL("按梯度",2);
	
	private String name;
	private Integer id;
	
	private ValuationWayEnum(String name, Integer id){
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
