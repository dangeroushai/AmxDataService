/*
 * 文件名：BaseController.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： BaseController.java
 * 修改人：hanrui
 * 修改时间：2016年4月7日
 * 修改内容：新增
 */
package com.amx.dataservice.service.impl;

import org.apache.log4j.Logger;

public abstract class BaseService {
	protected Logger logger;
	
	public BaseService() {
		this.logger = Logger.getLogger(this.getClass());
	}
}
