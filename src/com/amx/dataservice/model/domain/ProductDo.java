package com.amx.dataservice.model.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="product")
public class ProductDo {

    /**
     * @主键.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    /**
     * @名称.
     */
	@Column(nullable=false)
	private String name;
	
    /**
     * @创建时间.
     */
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="create_time",nullable=false)
    private Date createTime; 

    /**
     * @备注.
     */
	@Basic(fetch=FetchType.LAZY)
    private String remark; 

    /**
     * @编号.
     */
	@Column(nullable=false)
    private String no; 

    /**
     * @外币类型.
     */
    @Column(name="currency_id")
    private Integer currencyId; 

    /**
     * @产品简介.
     */
    private String introduce; 

    /**
     * @附图.
     */
    private String pictures; 

    /**
     * @属性权重.
     */
    @Column(name="attr_weight")
    private Integer attrWeight; 

    /**
     * @集合方式.
     */
    @Column(name="gather_way")
    private Integer gatherWay; 

    /**
     * @是否删除.
     */
    @Column(name="is_delete")
    private Boolean isDelete; 

    /**
     * @副标题.
     */
    @Column(name="sub_title")
    private String subTitle; 

    /**
     * @预售延迟.
     */
    @Column(name="presale_delay")
    private Integer presaleDelay; 

    /**
     * @英文名称.
     */
    @Column(name="en_name")
    private String enName; 

    /**
     * @主题权重.
     */
    @Column(name="theme_weight")
    private Integer themeWeight; 

    /**
     * @目的地权重.
     */
    @Column(name="dest_weight")
    private Integer destWeight; 

    /**
     * @例外日期.
     */
    @Column(name="exception_date")
    private String exceptionDate; 

    /**
     * @售卖规则.
     */
    @Column(name="sale_rule",length=7)
    private Integer saleRule; 

    /**
     * @产品介绍.
     */
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String description; 

    /**
     * @目的地.
     */
    @Column(name="dest_ids")
    private String destIds; 

    /**
     * @儿童政策.
     */
    @Column(name="child_rule")
    private String childRule; 

    /**
     * @更新时间.
     */
    @Temporal(TemporalType.TIMESTAMP) 
    @Column(name="modify_time")
    private Date modifyTime; 

    /**
     * @推荐产品.
     */
    @Column(name="recommend_ids")
    private String recommendIds; 

    /**
     * @服务语言.
     */
    @Column(name="language_ids")
    private String languageIds; 

    /**
     * @供应商.
     */
    @Column(name="supplier_id")
    private Integer supplierId; 

    /**
     * @集合坐标.
     */
    private String coordinate; 

    /**
     * @退改政策.
     */
    @Lob //longTxt
    @Column(name="refund_rule")
    @Basic(fetch=FetchType.LAZY)
    private String refundRule; 

    /**
     * @出发时间.
     */
    @Column(name="time_rule")
    private String timeRule; 

    /**
     * @预定须知.
     */
    @Lob
    @Column(name="book_rule")
    @Basic(fetch=FetchType.LAZY)
    private String bookRule; 

    /**
     * @集合地.
     */
    private String address; 

    /**
     * @封面图.
     */
    @Column(name="cover_pic")
    private String coverPic; 

    /**
     * @费用说明.
     */
    @Lob
    @Column(name="fee_des")
    @Basic(fetch=FetchType.LAZY)
    private String feeDes; 

    /**
     * @付款类型.
     */
    @Column(name="pay_type")
    private Integer payType; 

    /**
     * @售卖状态.
     */
    @Column(name="sale_state")
    private Integer saleState; 

    /**
     * @出发地.
     */
    @Column(name="start_city_ids")
    private String startCityIds; 

    /**
     * @产品类型.
     */
    @Column(name="type_id")
    private Integer typeId; 

    /**
     * @属性.
     */
    @Column(name="attr_ids")
    private String attrIds; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public Integer getAttrWeight() {
		return attrWeight;
	}

	public void setAttrWeight(Integer attrWeight) {
		this.attrWeight = attrWeight;
	}

	public Integer getGatherWay() {
		return gatherWay;
	}

	public void setGatherWay(Integer gatherWay) {
		this.gatherWay = gatherWay;
	}

	public Boolean isDelete() {
		return isDelete;
	}

	public void setDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getPresaleDelay() {
		return presaleDelay;
	}

	public void setPresaleDelay(Integer presaleDelay) {
		this.presaleDelay = presaleDelay;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public Integer getThemeWeight() {
		return themeWeight;
	}

	public void setThemeWeight(Integer themeWeight) {
		this.themeWeight = themeWeight;
	}

	public int getDestWeight() {
		return destWeight;
	}

	public void setDestWeight(int destWeight) {
		this.destWeight = destWeight;
	}

	public String getExceptionDate() {
		return exceptionDate;
	}

	public void setExceptionDate(String exceptionDate) {
		this.exceptionDate = exceptionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDestIds() {
		return destIds;
	}

	public void setDestIds(String destIds) {
		this.destIds = destIds;
	}

	public String getChildRule() {
		return childRule;
	}

	public void setChildRule(String childRule) {
		this.childRule = childRule;
	}


	public String getRecommendIds() {
		return recommendIds;
	}

	public void setRecommendIds(String recommendIds) {
		this.recommendIds = recommendIds;
	}

	public String getLanguageIds() {
		return languageIds;
	}

	public void setLanguageIds(String languageIds) {
		this.languageIds = languageIds;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public Integer getSaleRule() {
		return saleRule;
	}

	public void setSaleRule(Integer saleRule) {
		this.saleRule = saleRule;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getRefundRule() {
		return refundRule;
	}

	public void setRefundRule(String refundRule) {
		this.refundRule = refundRule;
	}

	public String getTimeRule() {
		return timeRule;
	}

	public void setTimeRule(String timeRule) {
		this.timeRule = timeRule;
	}

	public String getBookRule() {
		return bookRule;
	}

	public void setBookRule(String bookRule) {
		this.bookRule = bookRule;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getFeeDes() {
		return feeDes;
	}

	public void setFeeDes(String feeDes) {
		this.feeDes = feeDes;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public Integer getSaleState() {
		return saleState;
	}

	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
	}

	public String getStartCityIds() {
		return startCityIds;
	}

	public void setStartCityIds(String startCityIds) {
		this.startCityIds = startCityIds;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getAttrIds() {
		return attrIds;
	}

	public void setAttrIds(String attrIds) {
		this.attrIds = attrIds;
	}
}
