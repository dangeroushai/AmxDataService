package com.amx.dataservice.model.qo;



/**
 * 公共分页信息封装类
 * @author DangerousHai
 *
 */
public class PageQuery {

	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 页码
	 */
	private Integer pageIndex;
	/**
	 * 页容量
	 */
	private Integer pageSize;
	
	
	public PageQuery(Long userId2, int pageIndex2, int pageSize2) {
		this.pageIndex = pageIndex2;
		this.pageSize = pageSize2;
		this.userId = userId2;
	}
	public PageQuery() {
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
}
