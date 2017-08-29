package com.amx.dataservice.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.amx.dataservice.thrift.ThriftServer;

/**
 * Thrift 鐩戝惉鍣�
 * 
 * @author DangerousHai
 *
 */
public class InitThriftListener extends ContextLoaderListener {
	
	private ThriftServer thriftServerProxy = null; 

	@Override
	public void contextInitialized(ServletContextEvent event) {
		this.startAll(event);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// super.contextDestroyed(event);
		this.destoryAll(event);
	}

	/**
	 * 鎵�鏈夐渶瑕佽窡闅廤EB瀹瑰櫒涓�璧峰惎鍔ㄧ殑鎿嶄綔閮藉湪杩欓噷澶勭悊
	 * @param event
	 */
	private void startAll(ServletContextEvent event){
		//鑾峰彇IOC 瀹瑰櫒
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		//鑾峰彇Bean
		thriftServerProxy = context.getBean(ThriftServer.class);
		//鍚姩Thrift鏈嶅姟
		thriftServerProxy.startServer();
	}
	
	private void destoryAll(ServletContextEvent event){
		if(thriftServerProxy != null){
			thriftServerProxy.close();
		}
	}
	
}
