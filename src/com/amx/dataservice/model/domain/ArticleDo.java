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
@Table(name="article")
public class ArticleDo {


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
     * @点击量.
     */
	@Column(name="page_view")
    private Integer pageView; 

    /**
     * @权重.
     */
    private Integer weight; 

    /**
     * @摘要.
     */
    private String introduce; 

    /**
     * @来源网址.
     */
    @Column(name="source_url")
    private String sourceUrl; 


    /**
     * @正文.
     */
    private String content; 

    /**
     * @副标题.
     */
    @Column(name="sub_title")
    private String subTitle; 

    /**
     * @作者.
     */
    private String author; 

    /**
     * @来源.
     */
    private String source; 

    /**
     * @封面.
     */
    @Column(name="cover_pic")
    private String coverPic; 

    /**
     * @文章名.
     */
    private String name; 

    /**
     * @目的地ID.
     */
    @Column(name="dest_ids")
    private String destIds; 


    /**
     * @属性ID.
     */
    @Column(name="attr_ids")
    private String attrIds;


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


	public Integer getPageView() {
		return pageView;
	}


	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}


	public Integer getWeight() {
		return weight;
	}


	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	public String getIntroduce() {
		return introduce;
	}


	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}


	public String getSourceUrl() {
		return sourceUrl;
	}


	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getSubTitle() {
		return subTitle;
	}


	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
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


	public String getDestIds() {
		return destIds;
	}


	public void setDestIds(String destIds) {
		this.destIds = destIds;
	}


	public String getAttrIds() {
		return attrIds;
	}


	public void setAttrIds(String attrIds) {
		this.attrIds = attrIds;
	} 
    
}
