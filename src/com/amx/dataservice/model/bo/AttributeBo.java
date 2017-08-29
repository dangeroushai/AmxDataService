package com.amx.dataservice.model.bo;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.AttributeDo;


public class AttributeBo extends BaseBo<AttributeDo>{
	

	/**
     * @主键.
     */
    private Integer id; 
    
    /**
     * @类型ID.
     */
    private Integer typeId; 


    /**
     * @名称.
     */
    private String name;
    /**
     * @父ID.
     */
    private Integer parentId;
    
    /**
     * @封面图.
     */
    private String coverPic;
    
    
    public AttributeBo(AttributeDo ado){
    	init(ado);
    }
    
	@Override
	void init(AttributeDo obj) {
		if(obj != null){
    		BeanUtils.copyProperties(obj, this);
    	}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}
