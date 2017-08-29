package com.amx.dataservice.model.domain;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="user")
public class UserDo {
	/**
	 * @主键.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(fetch = FetchType.LAZY)
	private Long id; 
	
	 /**
     * @是否删除.
     */
	@Column(name="is_delete")
	@Basic(fetch = FetchType.LAZY)
    private Boolean isDelete; 

    /**
     * @创建时间.
     */
	@Column(name="create_time",updatable = false)
	@Temporal(TemporalType.TIMESTAMP) 
	@Basic(fetch = FetchType.LAZY)
    private Date createTime; 
	
    /**
     * @更新时间.
     */
    @Column(name="modify_time")
    @Temporal(TemporalType.TIMESTAMP) 
    @Basic(fetch = FetchType.LAZY)
    private Date modifyTime;
    
    /**
     * @是否可用.
     */
	@Column(name="is_enable")
	@Basic(fetch = FetchType.LAZY)
    private Boolean isEnable;


    /**
     * @区县.
     */
	@Basic(fetch = FetchType.LAZY)
    private String region; 

    /**
     * @电话.
     */
	@Basic(fetch = FetchType.LAZY)
	@Column(unique = true)
    private String phone; 

    /**
     * @最后登录时间.
     */
    @Column(name="login_time")
    @Basic(fetch = FetchType.LAZY)
    private Timestamp loginTime; 

    /**
     * @登陆状态.
     */
    @Column(name="login_status")
    @Basic(fetch = FetchType.LAZY)
    private Integer loginStatus; 

    /**
     * @备注.
     */
    @Basic(fetch = FetchType.LAZY)
    private String remark; 

    /**
     * @积分.
     */
    @Basic(fetch = FetchType.LAZY)
    private Integer score; 

    /**
     * @密码.
     */
    @Basic(fetch = FetchType.LAZY)
    private String password; 

    /**
     * @登陆类型.
     */
    @Basic(fetch = FetchType.LAZY)
    @Column(name="login_type")
    private Integer loginType; 

    /**
     * @城市.
     */
    @Basic(fetch = FetchType.LAZY)
    private String city; 

    /**
     * @最后登录IP.
     */
    @Column(name="login_ip")
    @Basic(fetch = FetchType.LAZY)
    private Integer loginIp; 

    /**
     * @用户名.
     */
    @Column(name="nick_name")
    private String nickName; 

    /**
     * @省（市）.
     */
    @Basic(fetch = FetchType.LAZY)
    private String province; 

    /**
     * @性别.
     */
    @Basic(fetch = FetchType.LAZY)
    private Integer gender; 
    
    
    private String passport;
    
    private Date birthday; 

    /**
     * @名.
     */
    @Basic(fetch = FetchType.LAZY)
    @Column(name="first_name")
    private String firstName; 


    /**
     * @用户角色.
     */
    @Basic(fetch = FetchType.LAZY)
    @Column(name="role_id")
    private Integer roleId; 

    /**
     * @姓.
     */
    @Basic(fetch = FetchType.LAZY)
    @Column(name="last_name")
    private String lastName; 

    /**
     * @国家代码.
     */
    @Basic(fetch = FetchType.LAZY)
    @Column(name="country_code")
    private String countryCode; 

    /**
     * @头像.
     */
    private String portrait; 

    /**
     * @国家.
     */
    @Basic(fetch = FetchType.LAZY)
    private String country; 

    /**
     * @邮箱.
     */
    @Basic(fetch = FetchType.LAZY)
    private String email; 

    /**
     * @注册时间.
     */
    @Basic(fetch = FetchType.LAZY)
    @Column(name="regist_time")
    private Timestamp registTime; 

    /**
     * @第三方ID.
     */
    @Column(name="open_id")
    @Basic(fetch = FetchType.LAZY)
    private String openId;

    public UserDo(){}
    
	public UserDo(Long userId) {
		this.id = userId;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(Integer loginIp) {
		this.loginIp = loginIp;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Timestamp registTime) {
		this.registTime = registTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	} 
    
}
