package com.amx.dataservice.model.bo;

import java.util.List;

import com.amx.dataservice.enums.AdLinkTypeEnum;
import com.amx.dataservice.model.domain.AdvertisementDo;
import com.amx.dataservice.model.domain.AdvertisementTypeDo;

public class AdvertisementBo extends BaseBo<AdvertisementDo>{
	
   /**
    * @主键.
    */
   private Integer id; 

    /**
     * @链接类型.
     */
    private Integer linkTypeId; 


    /**
     * @广告副标题.
     */
    private String subTitle; 


    /**
     * @广告标题.
     */
    private String title; 

    /**
     * @封面图.
     */
    private String coverPic; 
    private String effectPic; 
    
    /**
     * @链接内容.
     * 不提供getter / setter 
     */
    private String linkContent;
    
    /**
     * 链接产品
     */
    private Integer linkProductId;
    
    /**
     * 链接地址
     */
    private String linkUrl;
    
    /**
     * 链接文章ID
     */
    private Integer linkArticleId;
    /**
     * 链接地区ID
     */
    private Integer linkRegionId;
    /**
     * 链接属性ID
     */
    private Integer linkAttributeId;
    
    /**
     * 下属广告
     */
    private List<AdvertisementBo> subAdvertisementList;
    
    public AdvertisementBo(AdvertisementDo ado , AdvertisementTypeDo aTypedo ){
    	init(ado);
    	
    	/*根据链接类型设置不同链接属性值*/
    	this.linkTypeId = aTypedo.getLinkTypeId();
    	if(linkTypeId == AdLinkTypeEnum.PRODUCT.getId()){
    		this.linkProductId = Integer.parseInt(this.linkContent); 
			//this.linkProductTypeId = 
    	}else if(linkTypeId == AdLinkTypeEnum.ARTICLE.getId()){
    		this.setLinkArticleId(Integer.parseInt(this.linkContent));
    	}else if(linkTypeId == AdLinkTypeEnum.REGION.getId()){
    		this.setLinkRegionId(Integer.parseInt(this.linkContent));
    	}else if(linkTypeId == AdLinkTypeEnum.THEME.getId() || linkTypeId == AdLinkTypeEnum.SCENE.getId() ){
    		this.setLinkAttributeId(Integer.parseInt(this.linkContent));
    	}else if(linkTypeId == AdLinkTypeEnum.URL.getId()){
    		this.setLinkUrl(this.linkContent);
    	}else{//NULL
    		
    	}
    	//由外部设置
    	//this.subAdvertisementList
    }

	@Override
	void init(AdvertisementDo ado) {
		//BeanUtils.copyProperties(ado, this);
		this.id = ado.getId();
    	this.subTitle = ado.getSubTitle();
    	this.title = ado.getTitle();
    	this.coverPic = ado.getCoverPic();
    	this.effectPic = ado.getEffectPic();
    	this.linkContent = ado.getLinkContent();
	} 
 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLinkTypeId() {
		return linkTypeId;
	}

	public void setLinkTypeId(Integer linkTypeId) {
		this.linkTypeId = linkTypeId;
	}

	public String getEffectPic() {
		return effectPic;
	}

	public void setEffectPic(String effectPic) {
		this.effectPic = effectPic;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
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


	public Integer getLinkProductId() {
		return linkProductId;
	}



	public void setLinkProductId(Integer linkProductId) {
		this.linkProductId = linkProductId;
	}



	public String getLinkUrl() {
		return linkUrl;
	}



	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}



	public Integer getLinkArticleId() {
		return linkArticleId;
	}



	public void setLinkArticleId(Integer linkArticleId) {
		this.linkArticleId = linkArticleId;
	}



	public Integer getLinkRegionId() {
		return linkRegionId;
	}



	public void setLinkRegionId(Integer linkRegionId) {
		this.linkRegionId = linkRegionId;
	}



	public Integer getLinkAttributeId() {
		return linkAttributeId;
	}



	public void setLinkAttributeId(Integer linkAttributeId) {
		this.linkAttributeId = linkAttributeId;
	}



	public List<AdvertisementBo> getSubAdvertisementList() {
		return subAdvertisementList;
	}



	public void setSubAdvertisementList(List<AdvertisementBo> subAdvertisementList) {
		this.subAdvertisementList = subAdvertisementList;
	}



}
