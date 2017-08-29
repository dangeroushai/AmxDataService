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
@Table(name="region")
public class RegionDo {


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
     * @所属地区.
     */
	@Column(name="parent_id")
    private Integer parentId; 

    /**
     * @地区原始名称.
     */
	@Column(name="en_name")
    private String enName; 

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
     * @地区名称（中文）.
     */
    private String name;

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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
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
}
