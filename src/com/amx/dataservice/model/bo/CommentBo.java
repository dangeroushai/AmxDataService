package com.amx.dataservice.model.bo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.amx.dataservice.model.domain.CommentDo;
import com.amx.dataservice.util.StringUtil;

public class CommentBo extends BaseBo<CommentDo>{
	
    /**
     * @主键.
     */
    private Long id; 

    /**
     * @创建时间.
     */
    private Date createTime; 
	

    /**
     * @内容.
     */
    private String content; 

    /**
     * @用户Id.
     */
    private Long userId;
    
    /**
     * @用户Id.
     */
    private String userName;
    
    /**
     * @用户头像.
     */
    private String userPortrait; 

    /**
     * @附图.
     */
    private List<String> pictureList; 

    /**
     * @订单ID.
     */
    private Long orderId; 

    /**
     * @产品Id.
     */
    private Long productId;


    public CommentBo(){}
	public CommentBo(CommentDo cdo) {
		init(cdo);
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


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<String> pictureList) {
		this.pictureList = pictureList;
	}

	@Override
	void init(CommentDo obj) {
		if(obj != null){
			BeanUtils.copyProperties(obj, this);
			//处理图片
			this.pictureList = StringUtil.explodeStr2List(obj.getPictures());
			//处理名称
			this.userName = obj.getUser().getNickName();
			this.userPortrait = obj.getUser().getPortrait();
		}
	}

	public String getUserPortrait() {
		return userPortrait;
	}

	public void setUserPortrait(String userPortrait) {
		this.userPortrait = userPortrait;
	} 
}
