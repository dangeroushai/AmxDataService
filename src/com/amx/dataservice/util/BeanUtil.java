package com.amx.dataservice.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;



public class BeanUtil {

	/**
	 * 获取对象属性为null的属性名
	 * @param source
	 * @return
	 */
	private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || (srcValue instanceof String && "".equals(((String)srcValue).trim()))){
            	emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
	
	/**
	 * 属性拷贝（忽略非空属性）
	 * @param source
	 * @param target
	 */
	public static void copyNotNullProperties (Object source, Object target) {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}

}
