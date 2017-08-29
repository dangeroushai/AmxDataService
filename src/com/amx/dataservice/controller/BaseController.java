/*
 * 鏂囦欢鍚嶏細BaseController.java
 * 鐗堟潈锛欳opyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 鎻忚堪锛� BaseController.java
 * 淇敼浜猴細hanrui
 * 淇敼鏃堕棿锛�2016骞�4鏈�7鏃�
 * 淇敼鍐呭锛氭柊澧�
 */
package com.amx.dataservice.controller;

import org.apache.log4j.Logger;

public abstract class BaseController {
	protected Logger logger;
	
	public BaseController() {
		this.logger = Logger.getLogger(this.getClass());
	}
}
