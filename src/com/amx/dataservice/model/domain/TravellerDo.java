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


@Entity
@Table(name = "traveller")
public class TravellerDo {
	
	/**
	 * @主键.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * @是否删除.
	 */
	@Column(name = "is_delete")
	private Boolean isDelete;

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
     * @电话.
     */
    private String phone; 

    /**
     * @邮箱.
     */
    private String email; 

    /**
     * @是否负责人.
     */
    @Column(name = "is_principal")
    private Boolean isPrincipal; 

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
    @Column(name = "first_name")
    private String firstName; 

    /**
     * @订单ID.
     */
    @Column(name = "order_id")
    private Long orderId;


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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getIsPrincipal() {
		return isPrincipal;
	}


	public void setIsPrincipal(Boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
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


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
    
}
