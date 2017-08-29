package com.amx.dataservice.model.dto;

public abstract class BaseDto {
	/**
	 * 响应状态
	 */
	protected Boolean state;
	/**
	 * 响应数据
	 */
	protected Object data;
	/**
	 * 响应消息
	 */
	protected String msg;
	
	protected BaseDto( Boolean state , Object data , String msg){
		this.state = state;
		this.data = data;
		this.msg = msg;
	}
	
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
}
