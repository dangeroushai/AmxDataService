/*
 * 文件名：AttributeBo.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved.
 * 描述： AttributeBo.java
 * 修改人：leihaijun
 * 修改时间：Tue Mar 21 15:23:12 CST 2017
 * 修改内容：新增
 */
package com.amx.dataservice.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="attribute")
public class AttributeDo{


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
    private Integer id; 

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
     * @是否可用.
     */
	@Column(name="is_enable")
    private Boolean isEnable;

    /**
     * @父属性Id.
     */
	@Column(name="parent_id")
    private Integer parentId; 

    /**
     * @权重.
     */
    private Integer weight; 

    /**
     * @封面图.
     */
    @Column(name="cover_pic")
    private String coverPic; 

    /**
     * @属性名.
     */
    private String name; 



    /**
     * @类型.
     */
    @Column(name="type_id")
    private Integer typeId; 

    public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Boolean setIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
}
