package com.amx.dataservice.model.qo;



/**
 * 查询订单
 * @author DangerousHai
 *
 */
public class OrderQuery {

	/**
	 * 页码
	 */
	private Integer pageIndex;
	/**
	 * 页容量
	 */
	private Integer pageSize;
	/**
	 * 订单状态
	 */
	private Integer stateId;
	/**
	 * 用户ID
	 */
	private Long userId;
	
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
