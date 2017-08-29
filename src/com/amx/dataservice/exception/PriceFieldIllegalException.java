package com.amx.dataservice.exception;

public class PriceFieldIllegalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PriceFieldIllegalException(String msg, Exception e){
		super(msg, e);
	}
	public PriceFieldIllegalException(String msg){
		super(msg);
	}

}
