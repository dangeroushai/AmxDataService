package com.amx.dataservice.model.bo;



public abstract class BaseBo <T> {
	/**
	 * 
	 */
//	private static final long serialVersionUID = -1838073534932077424L;

	/**
	 * 将BO转换为DTO
	 */
	//abstract BaseDto convertToDto();
	
	abstract void init(T obj);
}
