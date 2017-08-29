package com.amx.dataservice.model.dto;


public class RequestDto {
	
	/**
	 * 请求服务
	 */
	private String servcie;
	/**
	 * 请求方法
	 */
	private String method;
	/**
	 * 请求数据
	 */
	private Object data;
	
	public String getServcie() {
		return servcie;
	}
	public void setServcie(String servcie) {
		this.servcie = servcie;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
