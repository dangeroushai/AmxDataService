package com.amx.dataservice.model.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.ProductDo;
import com.amx.dataservice.util.StringUtil;

/**
 * 产品业务对象，所有关联对象都只指明ID
 * @author DangerousHai
 *
 */
public class ProductBo extends BaseBo <ProductDo>{
	

	/**
     * @主键.
     */
	private Long id;
	
    /**
     * @名称.
     */
	private String name;
	
    /**
     * @编号.
     */
    private String no; 
    
    /**
     * @起始价格.
     */
    private Double price; 

    /**
     * @外币类型.
     */
    private Integer currencyId; 

    /**
     * @产品简介.
     */
    private List<String> introduceList; 

    /**
     * @附图<路径,描述>.
     */
    private Map<String, String> pictureMap; 

    /**
     * @集合方式.
     */
    private Integer gatherWay; 

    /**
     * @副标题.
     */
    private String subTitle; 

    /**
     * @预售延迟.
     */
    private Integer presaleDelay; 

    /**
     * @例外日期.
     */
    private List<String> exceptionDateList; 

    /**
     * @售卖规则.
     */
    private List<Integer> saleRuleList; 

    /**
     * @产品介绍.
     */
    private String description; 

    /**
     * @目的地.
     */
    private List<Integer> destIdList;

    /**
     * @儿童政策.
     */
    private String childRule; 


    /**
     * @推荐产品.
     */
    private List<Integer> recommendIdList; 

    /**
     * @服务语言.
     */
    private List<Integer> languageIdList; 
    
    /**
     * @集合地.
     */
    private String address; 

    /**
     * @经度.
     */
    private String longitude;
    
    /**
     * @纬度.
     */
    private String latitude; 
    
    /**
     * @展示时长.
     */
    private Float duration; 

    /**
     * @退改政策.
     */
    private String refundRule; 

    /**
     * @出发时间.
     */
    private List<String> timeRuleList; 

    /**
     * @预定须知.
     */
    private String bookRule; 

    /**
     * @封面图.
     */
    private String coverPic; 

    /**
     * @费用说明.
     */
    private String feeDes; 

    /**
     * @付款类型.
     */
    private Integer payType; 

    /**
     * @出发地.
     */
    private List<Integer> startCityIdList; 

    /**
     * @产品类型.
     */
    private Integer typeId; 

    /**
     * @属性.
     */
    private List<Integer> attrIdList; 
    
	/**
	 * 套餐
	 */
	private List<Integer> packageIdList;
	
	/**
	 * 行程安排
	 */
	private List<Integer> hodometerIdList;
	
	   /**
     * @成人上限.
     */
    private Integer maxAdultNum; 
    
    /**
     * @人数上限.
     */
    private Integer maxPersonNum; 
    /**
     * @人数下限.
     */
    private Integer minPersonNum; 

    /**
     * @儿童上限.
     */
    private Integer maxChildNum;
	
	/**
	 * 使用do 构造  bo
	 * @param pdo
	 */
	public ProductBo(ProductDo pdo){
		init(pdo);
	}
	
	@Override
	public void init(ProductDo pdo){
		/*拷贝基本属性*/
		BeanUtils.copyProperties(pdo, this);
		/*处理多值属性*/
		if(pdo.getIntroduce().length() > 0){
			this.introduceList = Arrays.asList(pdo.getIntroduce().substring(1).split(">"));
		}
		this.attrIdList = StringUtil.explodeIdStr2IdList(pdo.getAttrIds());
		this.destIdList = StringUtil.explodeIdStr2IdList(pdo.getDestIds());
		this.startCityIdList = StringUtil.explodeIdStr2IdList(pdo.getStartCityIds());
		this.recommendIdList = StringUtil.explodeIdStr2IdList(pdo.getRecommendIds());
		this.languageIdList = StringUtil.explodeIdStr2IdList(pdo.getLanguageIds());
		this.exceptionDateList = StringUtil.explodeStr2List(pdo.getExceptionDate());
		
		this.timeRuleList = StringUtil.explodeStr2List(pdo.getTimeRule());
		/*售卖星期*/
		this.saleRuleList = new ArrayList<Integer>();
		/*将数据库中的二进制处理为7位字符数组*/
		char[] saleRule = {'0','0','0','0','0','0','0'}; 
		char[] charArray = Integer.toBinaryString(pdo.getSaleRule()).toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			int currIndex = 7 - charArray.length + i;
			saleRule[currIndex] = charArray[i]; 
		}
		/*将7位字符数组处理为指代星期的数字*/
		int week = 0;
		for(char isEnable : saleRule){
			 if(isEnable == '1'){
				 this.saleRuleList.add(week);
			 }
			 week ++;
		}
		/*经纬度*/
		List<String> coordinate = StringUtil.explodeStr2List(pdo.getCoordinate());
		try {
			this.latitude = coordinate.get(0).trim();
			this.longitude = coordinate.get(1).trim();
		} catch (Exception e) {}
		/*处理附属图片*/
		List<String> picList = StringUtil.explodeStr2List(pdo.getPictures());
		if(picList != null && picList.size() != 0){
			this.pictureMap = new LinkedHashMap<String, String>();
			for(String pic : picList){
				//String[] picInfo = pic.split("|");
				//0-picUrl;1-picAlt
				this.pictureMap.put(pic.replace("|", ""), null);
			}
		}
		
		/*外部关联属性 - 由业务层处理*/
		/*this.hodometerIdList
		this.packageIdList*/
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public Integer getMaxAdultNum() {
		return maxAdultNum;
	}

	public void setMaxAdultNum(Integer maxAdultNum) {
		this.maxAdultNum = maxAdultNum;
	}

	public Integer getMaxPersonNum() {
		return maxPersonNum;
	}

	public void setMaxPersonNum(Integer maxPersonNum) {
		this.maxPersonNum = maxPersonNum;
	}

	public Integer getMinPersonNum() {
		return minPersonNum;
	}

	public void setMinPersonNum(Integer minPersonNum) {
		this.minPersonNum = minPersonNum;
	}

	public Integer getMaxChildNum() {
		return maxChildNum;
	}

	public void setMaxChildNum(Integer maxChildNum) {
		this.maxChildNum = maxChildNum;
	}

	public void setName(String name) {
		this.name = name;
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


	public List<String> getIntroduce() {
		return introduceList;
	}


	public void setIntroduce(List<String> introduceList) {
		this.introduceList = introduceList;
	}


	public Map<String, String> getPictureMap() {
		return pictureMap;
	}


	public void setPictureMap(Map<String, String> pictureMap) {
		this.pictureMap = pictureMap;
	}


	public Integer getGatherWay() {
		return gatherWay;
	}


	public void setGatherWay(Integer gatherWay) {
		this.gatherWay = gatherWay;
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


	public List<String> getExceptionDateList() {
		return exceptionDateList;
	}


	public void setExceptionDateList(List<String> exceptionDateList) {
		this.exceptionDateList = exceptionDateList;
	}


	public List<Integer> getSaleRuleList() {
		return saleRuleList;
	}


	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public void setSaleRuleList(List<Integer> saleRuleList) {
		this.saleRuleList = saleRuleList;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Integer> getDestIdList() {
		return destIdList;
	}


	public void setDestIdList(List<Integer> destIdList) {
		this.destIdList = destIdList;
	}


	public String getChildRule() {
		return childRule;
	}


	public void setChildRule(String childRule) {
		this.childRule = childRule;
	}


	public List<Integer> getRecommendIdList() {
		return recommendIdList;
	}


	public void setRecommendIdList(List<Integer> recommendIdList) {
		this.recommendIdList = recommendIdList;
	}


	public List<Integer> getLanguageIdList() {
		return languageIdList;
	}


	public void setLanguageIdList(List<Integer> languageIdList) {
		this.languageIdList = languageIdList;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getRefundRule() {
		return refundRule;
	}


	public void setRefundRule(String refundRule) {
		this.refundRule = refundRule;
	}


	public List<String> getTimeRuleList() {
		return timeRuleList;
	}


	public void setTimeRuleList(List<String> timeRuleList) {
		this.timeRuleList = timeRuleList;
	}


	public String getBookRule() {
		return bookRule;
	}


	public void setBookRule(String bookRule) {
		this.bookRule = bookRule;
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


	public void setPayType(Integer payType) {
		this.payType = payType;
	}


	public List<Integer> getStartCityIdList() {
		return startCityIdList;
	}


	public void setStartCityIdList(List<Integer> startCityIdList) {
		this.startCityIdList = startCityIdList;
	}


	public Integer getTypeId() {
		return typeId;
	}


	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}


	public List<Integer> getAttrIdList() {
		return attrIdList;
	}


	public void setAttrIdList(List<Integer> attrIdList) {
		this.attrIdList = attrIdList;
	}


	public List<Integer> getPackageIdList() {
		return packageIdList;
	}


	public void setPackageIdList(List<Integer> packageIdList) {
		this.packageIdList = packageIdList;
	}


	public List<Integer> getHodometerIdList() {
		return hodometerIdList;
	}

	public void setHodometerIdList(List<Integer> hodometerIdList) {
		this.hodometerIdList = hodometerIdList;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<String> getIntroduceList() {
		return introduceList;
	}

	public void setIntroduceList(List<String> introduceList) {
		this.introduceList = introduceList;
	}
	
}
