package com.amx.dataservice.model.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.ArticleDo;

public class ArticleBo  extends BaseBo<ArticleDo>{

    /**
     * @点击量.
     */
    private Integer pageView; 


    /**
     * @摘要.
     */
    private String introduce; 

    /**
     * @来源网址.
     */
    private String sourceUrl; 


    /**
     * @主键.
     */
    private Integer id; 

    /**
     * @正文.
     */
    private String content; 

    /**
     * @副标题.
     */
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
    private String coverPic; 

    /**
     * @文章名.
     */
    private String name; 
    
    private Date createTime; 

    /**
     * @目的地ID.
     */
    private List<Integer> destIdList; 

    /**
     * @属性ID.
     */
    private List<Integer>  attrIdList;
    
    public ArticleBo(ArticleDo ado){
    	init(ado);
    }

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Integer> getDestIdList() {
		return destIdList;
	}

	public void setDestIdList(List<Integer> destIdList) {
		this.destIdList = destIdList;
	}

	public List<Integer> getAttrIdList() {
		return attrIdList;
	}

	public void setAttrIdList(List<Integer> attrIdList) {
		this.attrIdList = attrIdList;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	void init(ArticleDo obj) {
		if(obj != null){
    		BeanUtils.copyProperties(obj, this);
    		this.attrIdList = new ArrayList<Integer>();
    		String[] split = obj.getAttrIds().split(",");
    		for (int i = 0; i < split.length; i++) {
    			this.attrIdList.add(Integer.parseInt(split[i]));
			}
    	}
	} 
}
