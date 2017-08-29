package com.amx.dataservice.enums;

/**
 * 产品类型
 * @author DangerousHai
 *
 */
public enum GenderTypeEnum {
	
	MAN("男",1),WOMAN("女",2),UNKONW("保密",3);
	
	private String name;
	private Integer id;
	
	private GenderTypeEnum(String name, Integer id){
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
