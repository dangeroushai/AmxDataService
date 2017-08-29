package com.amx.dataservice.model.domain;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
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
@Table(name="orders")
public class OrderDo {
	
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
     * @是否删除.
     */
	@Column(name="is_delete")
    private Boolean isDelete; 
	

    /**
     * @订单状态.
     */
	@Column(name="order_state_id")
    private Integer orderStateId; 


    /**
     * @儿童单价.
     */
	@Column(name="child_price")
    private BigDecimal childPrice; 

    /**
     * @产品类型ID.
     */
	@Column(name="product_type_id")
    private Integer productTypeId; 

    /**
     * @用户备注.
     */
    private String remark; 

    /**
     * @付款信息.
     */
    @Column(name="pay_info")
    private String payInfo; 

    /**
     * @订单编号.
     */
    private String no; 

    /**
     * @待付币种.
     */
    @Column(name="currency_id")
    private Integer currencyId; 

    /**
     * @套餐ID.
     */
    @Column(name="package_id")
    private Long packageId; 

    /**
     * @出发日期.
     */
    @Column(name="go_off_date")
    private Date goOffDate; 

    /**
     * @付款时间.
     */
    @Column(name="pay_time")
    private Timestamp payTime; 

    /**
     * @集合方式.
     */
    @Column(name="gather_way")
    private Integer gatherWay; 

    /**
     * @待付金额.
     */
    private BigDecimal obligation; 

    /**
     * @接送酒店.
     */
    @Column(name="hotel_name")
    private String hotelName; 

    /**
     * @用户ID.
     */
    @Column(name="user_id")
    private Long userId; 

    /**
     * @成人数.
     */
    @Column(name="adult_num")
    private Integer adultNum; 

    /**
     * @系统备注.
     */
    @Column(name="sys_remark")
    private String sysRemark; 

    /**
     * @儿童数.
     */
    @Column(name="child_num")
    private Integer childNum; 

    /**
     * @服务费.
     */
    @Column(name="service_fee")
    private BigDecimal serviceFee; 

    /**
     * @交易编号.
     */
    @Column(name="trade_no")
    private String tradeNo; 

    /**
     * @成人单价.
     */
    @Column(name="adult_price")
    private BigDecimal adultPrice; 

    /**
     * @下单时间.
     */
    @Column(name="order_time")
    private Timestamp orderTime; 

    /**
     * @优惠码.
     */
    private String udid; 

    /**
     * @语言ID.
     */
    @Column(name="language_id")
    private Integer languageId; 

    /**
     * @产品ID.
     */
    @Column(name="product_id")
    private Long productId; 

    /**
     * @酒店地址.
     */
    @Column(name="hotel_addr")
    private String hotelAddr; 

    /**
     * @优惠金额.
     */
    private BigDecimal discount; 

    /**
     * @订单总价.
     */
    private BigDecimal price; 

    /**
     * @出发时间.
     */
    @Column(name="go_off_time")
    private Time goOffTime; 

    /**
     * @预付金额.
     */
    @Column(name="pre_pay_price")
    private BigDecimal prePayPrice; 

    /**
     * @付款类型.
     */
    @Column(name="pay_type")
    private Integer payType; 

    /**
     * @付款方式.
     */
    @Column(name="pay_way")
    private Integer payWay; 

    /**
     * @实付金额.
     */
    @Column(name="real_payment")
    private BigDecimal realPayment;

    
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

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getOrderStateId() {
		return orderStateId;
	}

	public void setOrderStateId(Integer orderStateId) {
		this.orderStateId = orderStateId;
	}

	public BigDecimal getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(BigDecimal childPrice) {
		this.childPrice = childPrice;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
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

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public Date getGoOffDate() {
		return goOffDate;
	}

	public void setGoOffDate(Date goOffDate) {
		this.goOffDate = goOffDate;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public Integer getGatherWay() {
		return gatherWay;
	}

	public void setGatherWay(Integer gatherWay) {
		this.gatherWay = gatherWay;
	}

	public BigDecimal getObligation() {
		return obligation;
	}

	public void setObligation(BigDecimal obligation) {
		this.obligation = obligation;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}

	public String getSysRemark() {
		return sysRemark;
	}

	public void setSysRemark(String sysRemark) {
		this.sysRemark = sysRemark;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public BigDecimal getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(BigDecimal adultPrice) {
		this.adultPrice = adultPrice;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getHotelAddr() {
		return hotelAddr;
	}

	public void setHotelAddr(String hotelAddr) {
		this.hotelAddr = hotelAddr;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Time getGoOffTime() {
		return goOffTime;
	}

	public void setGoOffTime(Time goOffTime) {
		this.goOffTime = goOffTime;
	}

	public BigDecimal getPrePayPrice() {
		return prePayPrice;
	}

	public void setPrePayPrice(BigDecimal prePayPrice) {
		this.prePayPrice = prePayPrice;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public BigDecimal getRealPayment() {
		return realPayment;
	}

	public void setRealPayment(BigDecimal realPayment) {
		this.realPayment = realPayment;
	} 

}
