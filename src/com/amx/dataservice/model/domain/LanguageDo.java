/*
 * 文件名：LanguageDo.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved.
 * 描述： LanguageDo.java
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
@Table(name="language")
public class LanguageDo {

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
    private int id; 

    /**
     * @创建时间.
     */
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP) 
    private Date createTime; 

    /**
     * @是否可用.
     */
	@Column(name="is_enable")
    private boolean isEnable; 

    /**
     * @原始名称.
     */
	@Column(name="en_name")
    private String enName; 

    /**
     * @适用地区.
     */
	@Column(name="region_ids")
    private String regionIds; 

    /**
     * @语言名称（中文）.
     */
    private String name; 

    /**
     * @更新时间.
     */
    @Column(name="modify_time")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date modifyTime;

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getRegionIds() {
		return regionIds;
	}

	public void setRegionIds(String regionIds) {
		this.regionIds = regionIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
