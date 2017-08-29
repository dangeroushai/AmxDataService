package com.amx.dataservice.model.bo;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.RegionDo;



public class RegionBo extends BaseBo<RegionDo>{


    /**
     * @主键.
     */
    private Integer id; 


    /**
     * @所属地区.
     */
    private Integer parentId; 

    /**
     * @地区原始名称.
     */
    private String enName; 


    /**
     * @封面图.
     */
    private String coverPic; 

    /**
     * @地区名称（中文）.
     */
    private String name;
    
    public RegionBo(RegionDo rdo){
    	init(rdo);
    }
    
	@Override
	void init(RegionDo rdo) {
		/*拷贝基本属性*/
		BeanUtils.copyProperties(rdo, this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
    
}
