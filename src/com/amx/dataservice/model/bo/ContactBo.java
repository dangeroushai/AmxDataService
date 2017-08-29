/*
 * 文件名：User_ContactBo.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved.
 * 描述： User_ContactBo.java
 * 修改人：leihaijun
 * 修改时间：Tue Mar 21 15:23:12 CST 2017
 * 修改内容：新增
 */
package com.amx.dataservice.model.bo;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.ContactDo;

public class ContactBo extends BaseBo<ContactDo>{
	

	/**
	 * @主键.
	 */
	private Long id;

    /**
     * @姓.
     */
    private String lastName; 

    /**
     * @电话.
     */
    private String phone; 
    /**
     * @国家代码.
     */
    private String countryCode; 

    /**
     * @邮箱.
     */
    private String email; 

    /**
     * @所属用户ID.
     */
    private Long userId; 

    /**
     * @性别.
     */
    private Integer gender; 

    /**
     * @护照.
     */
    private String passport; 

    /**
     * @名.
     */
    private String firstName;
    
    public ContactBo(){}
    
    public ContactBo(ContactDo cdo){
    	init(cdo);
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	void init(ContactDo cdo) {
		BeanUtils.copyProperties(cdo, this);
		//检查电话信息是否含国家代码
		String[] split = cdo.getPhone().split(" ");
		if(split.length == 2){
			this.countryCode = split[0];
			this.phone = split[1];
		}
	} 
}
