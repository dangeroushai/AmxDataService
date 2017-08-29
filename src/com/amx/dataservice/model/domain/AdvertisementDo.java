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
@Table(name="advertisement")
public class AdvertisementDo {
	

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
     * @权重.
     */
    private Integer weight; 

    /**
     * @备注.
     */
    private String remark; 

    /**
     * @类型.
     */
    @Column(name="ad_type_id")
    private Integer adTypeId; 


    /**
     * @广告副标题.
     */
    @Column(name="sub_title")
    private String subTitle; 

    /**
     * @上级.
     */
    @Column(name="parent_id")
    private int parentId; 

    /**
     * @广告标题.
     */
    private String title; 

    /**
     * @封面图.
     */
    @Column(name="cover_pic")
    private String coverPic;
    
    @Column(name="effect_pic")
//    @Basic(fetch = FetchType.LAZY)
    private String effectPic; 

    /**
     * @广告名称.
     */
    private String name; 

    /**
     * @链接内容.
     */
    @Column(name="link_content")
    private String linkContent;

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

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getAdTypeId() {
		return adTypeId;
	}

	public void setAdTypeId(Integer adTypeId) {
		this.adTypeId = adTypeId;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getLinkContent() {
		return linkContent;
	}

	public void setLinkContent(String linkContent) {
		this.linkContent = linkContent;
	}

	public String getEffectPic() {
		return effectPic;
	}

	public void setEffectPic(String effectPic) {
		this.effectPic = effectPic;
	} 
 
}
