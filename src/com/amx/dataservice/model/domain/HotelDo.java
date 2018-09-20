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
@Table(name="hotel")
public class HotelDo {

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
     * @编号.
     */
	@Column(nullable=false)
    private String no; 

    /**
     * @产品简介.
     */
    private String introduce; 

    /**
     * @附图.
     */
    private String pictures; 

    /**
     * @归属地.
     */
    @Column(name="dest_ids")
    private String destIds; 

    /**
     * @更新时间.
     */
    @Temporal(TemporalType.TIMESTAMP) 
    @Column(name="modify_time")
    private Date modifyTime; 

    /**
     * @坐标.
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
     * @地址.
     */
    private String address; 

    /**
     * @封面图.
     */
    @Column(name="cover_pic")
    private String coverPic; 


    /**
     * @售卖状态.
     */
    @Column(name="sale_state")
    private Integer saleState; 

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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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

	public String getDestIds() {
		return destIds;
	}

	public void setDestIds(String destIds) {
		this.destIds = destIds;
	}

	public String getCoordinate() {
		return coordinate;
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

	public Integer getSaleState() {
		return saleState;
	}

	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
	}
}
