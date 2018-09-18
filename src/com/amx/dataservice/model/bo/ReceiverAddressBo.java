package com.amx.dataservice.model.bo;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.ReceiverAddressDo;

/**
 * 收货地址
 * @author leihaijun
 * @Title
 * @Descrption
 * @date 2018年9月17日 上午9:51:08
 * @Modified By
 */
public class ReceiverAddressBo extends BaseBo <ReceiverAddressDo>{
	
	/**
	 * @是否删除.
	 */
	private Boolean isDelete;

	/**
	 * @主键.
	 */
	@Id
	private Long id;

	/**
	 * @创建时间.
	 */
	private Date createTime;

	/**
	 * @更新时间.
	 */
	private Date modifyTime;

    /**
     * @姓.
     */
    private String lastName; 
	/**
	 * @名.
	 */
	private String firstName;

    /**
     * @电话.
     */
    private String phone; 
    
    /**
     * @省ID.
     */
    private Long provinceAreaId; 
    
    /**
     * @市ID.
     */
    private Long cityAreaId; 
    
    /**
     * @区/县ID.
     */
    private Long countyAreaId; 

    /**
     * @详细地址.
     */
    private String address; 
    
    /**
     * @所属用户ID.
     */
    private Long userId; 
    
    /**
	 * @是否默认收货地址.
	 */
	private Boolean isDefault;


	public ReceiverAddressBo(ReceiverAddressDo pdo) {
		init(pdo);
	}
	
	@Override
	public void init(ReceiverAddressDo pdo){
		/*拷贝基本属性*/
		BeanUtils.copyProperties(pdo, this);
	}

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
