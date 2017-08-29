package com.amx.dataservice.model.dto;

import java.util.HashMap;
import java.util.Map;

public class ResponseDto {
	/**
	 * 响应状态
	 */
	private Boolean state;
	/**
	 * 响应数据
	 */
	private Object data;
	/**
	 * 响应消息
	 */
	private String msg;
	
	public ResponseDto(){}
	
	public ResponseDto( Boolean state , Object data , String msg){
		this.state = state;
		this.data = data;
		this.msg = msg;
	}
	
	@Deprecated
	public static Map<String, Object> getResult(boolean state, Object data, String msg){
		Map<String, Object> result = new HashMap<String, Object>(); 
		result.put("State", state);
		result.put("data", data);
		result.put("msg", msg);
		
		return result;	
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
