package com.amx.dataservice.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 中国地区信息（来源高德地图）
 * 高德地图‘行政区域查询’API： http://lbs.amap.com/api/webservice/guide/api/district/
 * @author leihaijun
 * @Title
 * @Descrption
 * @date 2018年9月17日 上午10:43:07
 * @Modified By
 */
@Entity
@Table(name = "area")
public class AreaDo {
	
	/**
	 * @主键.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    /**
     * @姓.
     */
	@Column(name = "area_code")
    private String areaCode;
	
	private String name; 

    /**
     * @城市级别.
     */
    private Integer level; 
    
    /**
     * @姓.
     */
	@Column(name = "city_code")
    private String cityCode;
	
	/**
	 * 城市中心经纬度
	 */
	private String center;
	
	@Column(name = "parent_id")
	private Integer parentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
