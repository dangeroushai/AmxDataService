package com.amx.dataservice.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*@NamedNativeQueries({
	@NamedNativeQuery(
		name = "findAllByproductId",
		query = "SELECT c.*,u.nick_name user_name FROM comment c LEFT JOIN user u ON c.user_id = u.id WHERE c.is_delete = 0 AND c.is_enable = 1 AND c.product_id = :pid ORDER BY c.create_time DESC",
		resultSetMapping = "commentMap")
})
@SqlResultSetMappings({
	@SqlResultSetMapping(
		name = "commentMap",
		entities = {},
		columns = {
			@ColumnResult(name = "id"),	
			@ColumnResult(name = "content"),	
			@ColumnResult(name = "pictures"),	
			@ColumnResult(name = "user_name"),	
		})
})*/


@Entity
@Table(name="comment")
public class CommentDo {
	
	 /**
     * @是否删除.
     */
	@Column(name="is_delete")
    private Boolean isDelete; 

    /**
     * @主键.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    /**
     * @创建时间.
     */
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP) 
    private Date createTime; 
	
    /**
     * @更新时间.
     */
    @Column(name="modify_time")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date modifyTime;


    /**
     * @内容.
     */
    private String content; 

    /**
     * @是否可用.
     */
    @Column(name="is_enable")
    private Boolean isEnable; 

/*
    @Column(name="user_id")
    private Long userId;
*/    
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UserDo.class)
   /* @JoinTable(
		name = "user",
		joinColumns = {
    		//@JoinColumn(name = "user_id",referencedColumnName = "id")
    })*/
    @JoinColumn(name = "user_id")
    private UserDo user;
    

    /**
     * @附图.
     */
    private String pictures; 

    /**
     * @订单ID.
     */
    @Column(name="order_id")
    private Long orderId; 

    /**
     * @产品Id.
     */
    @Column(name="product_id")
    private Long productId;

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public UserDo getUser() {
		return user;
	}

	public void setUser(UserDo user) {
		this.user = user;
	}

}
