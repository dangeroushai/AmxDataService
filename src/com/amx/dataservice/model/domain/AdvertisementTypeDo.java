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
@Table(name = "advertisementType")
public class AdvertisementTypeDo {

	/**
	 * @是否删除.
	 */
	@Column(name = "is_delete")
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
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/**
	 * @更新时间.
	 */
	@Column(name = "modify_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;

	/**
	 * @上级.
	 */
	@Column(name="parent_id")
	private Integer parentId;

	/**
	 * @备注.
	 */
	private String remark;

	/**
	 * @链接类型.
	 */
	@Column(name="link_type_id")
	private Integer linkTypeId;

	/**
	 * @标题.
	 */
	private String name;

	/**
	 * @识别码（重要）.
	 */
	private String udid;

	/**
	 * @类型（作用域）.
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


	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLinkTypeId() {
		return linkTypeId;
	}

	public void setLinkTypeId(Integer linkTypeId) {
		this.linkTypeId = linkTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}
