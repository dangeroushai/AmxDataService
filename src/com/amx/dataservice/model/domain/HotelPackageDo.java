
package com.amx.dataservice.model.domain;

import java.math.BigDecimal;
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
@Table(name="hotel_package")
public class HotelPackageDo {

    /**
     * @主键.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    /**
     * @更新时间.
     */
    @Column(name="modify_time")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date modifyTime;
    
    /**
     * @创建时间.
     */
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP) 
    private Date createTime; 
	
    /**
     * @权重.
     */
    private Integer weight; 

    /**
     * @备注.
     */
    private String remark; 

    /**
     * @价格.
     */
    private BigDecimal price; 

    /**
     * @面积
     */
    private Double area;

    /**
     * @所属产品.
     */
    @Column(name="hotel_id")
    private Integer hotelId; 

    /**
     * @成人上限.
     */
    @Column(name="max_adult_num")
    private Integer maxAdultNum; 

    /**
     * @儿童上限.
     */
    @Column(name="max_child_num")
    private Integer maxChildNum;

    /**
     * @介绍.
     */
    private String description; 

    /**
     * @套餐名称.
     */
    private String name;
    
    @Column(name="bathroom_device_ids")
    private String bathroomDeviceIds;
    
    @Column(name="room_device_ids")
    private String roomDeviceIds;
    
    @Column(name="food_device_ids")
    private String foodDeviceIds;
    
    /**
     * @附图.
     */
    private String pictures; 
    
    /**
     * @封面图.
     */
    @Column(name="cover_pic")
    private String coverPic; 
    
    /**
     * @退改政策.
     */
    @Lob //longTxt
    @Column(name="refund_rule")
    @Basic(fetch=FetchType.LAZY)
    private String refundRule; 

    /**
     * @售卖状态.
     */
    @Column(name="sale_state")
    private Integer saleState; 
    
    /**
     * @库存.
     */
    private Integer stock; 


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getMaxChildNum() {
		return maxChildNum;
	}

	public void setMaxChildNum(Integer maxChildNum) {
		this.maxChildNum = maxChildNum;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getMaxAdultNum() {
		return maxAdultNum;
	}

	public void setMaxAdultNum(Integer maxAdultNum) {
		this.maxAdultNum = maxAdultNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBathroomDeviceIds() {
		return bathroomDeviceIds;
	}

	public void setBathroomDeviceIds(String bathroomDeviceIds) {
		this.bathroomDeviceIds = bathroomDeviceIds;
	}

	public String getRoomDeviceIds() {
		return roomDeviceIds;
	}

	public void setRoomDeviceIds(String roomDeviceIds) {
		this.roomDeviceIds = roomDeviceIds;
	}

	public String getFoodDeviceIds() {
		return foodDeviceIds;
	}

	public void setFoodDeviceIds(String foodDeviceIds) {
		this.foodDeviceIds = foodDeviceIds;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getRefundRule() {
		return refundRule;
	}

	public void setRefundRule(String refundRule) {
		this.refundRule = refundRule;
	}

	public Integer getSaleState() {
		return saleState;
	}

	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}     
}
