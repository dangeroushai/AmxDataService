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

/**
 * 收货地址
 * @author leihaijun
 * @Title
 * @Descrption
 * @date 2018年9月17日 上午9:51:08
 * @Modified By
 */
@Entity
@Table(name = "receiver_address")
public class ReceiverAddressDo {
	
	/**
	 * @是否删除.
	 */
	@Column(name = "is_delete")
	private Boolean isDelete;

	/**
	 * @主键.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * @创建时间.
	 */
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/**
	 * @更新时间.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	private Date modifyTime;

    /**
     * @姓.
     */
	@Column(name = "last_name")
    private String lastName; 
	/**
	 * @名.
	 */
	@Column(name = "first_name")
	private String firstName;

    /**
     * @电话.
     */
    private String phone; 
    
    /**
     * @省ID.
     */
    @Column(name = "province_area_id")
    private Long provinceAreaId; 
    
    /**
     * @市ID.
     */
    @Column(name = "city_area_id")
    private Long cityAreaId; 
    
    /**
     * @区/县ID.
     */
    @Column(name = "county_area_id")
    private Long countyAreaId; 

    /**
     * @详细地址.
     */
    private String address; 
    
    /**
     * @所属用户ID.
     */
    @Column(name = "user_id")
    private Long userId; 
    
    /**
	 * @是否默认收货地址.
	 */
	@Column(name = "is_default")
	private Boolean isDefault;


	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getProvinceAreaId() {
		return provinceAreaId;
	}

	public void setProvinceAreaId(Long provinceAreaId) {
		this.provinceAreaId = provinceAreaId;
	}

	public Long getCityAreaId() {
		return cityAreaId;
	}

	public void setCityAreaId(Long cityAreaId) {
		this.cityAreaId = cityAreaId;
	}

	public Long getCountyAreaId() {
		return countyAreaId;
	}

	public void setCountyAreaId(Long countyAreaId) {
		this.countyAreaId = countyAreaId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	} 
}
