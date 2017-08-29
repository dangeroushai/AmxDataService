package com.amx.dataservice.listener;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

/**
 * properties属性文件初始化监听器
 * 
 * @author DangerousHai
 *
 */
public class InitPropertiesListener extends ContextLoaderListener {

	private static final String PROPERTIES_LOCATION_PARAM = "propertiesLocation";
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		this.initProperties(event);
		
		// LogUtil.recordWarnLog(System.getProperty("jdbc.host"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// super.contextDestroyed(event);
	}

	/**
	 * 初始化属性配置文件配置
	 */
	private void initProperties(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		String configLocationParam = sc
				.getInitParameter(PROPERTIES_LOCATION_PARAM);
		List<String> configFiles = Arrays
				.asList(configLocationParam.split(","));
		for (String configFile : configFiles) {
			Properties properties = new Properties();
			try {
				properties.load(getClass().getResourceAsStream(
						configFile.trim()));
				for (String prop : properties.stringPropertyNames()) {
					if (System.getProperty(prop) == null) {
						System.setProperty(prop, properties.getProperty(prop));
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
