package com.amx.dataservice.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

public class GenericTypeTest {
	
	public List<Long> list;
	
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		Class<GenericTypeTest> clazz = GenericTypeTest.class;
	/*	
		Field field = clazz.getDeclaredField("list");
		
		Class<?> type = field.getType();
		
		type.isAssignableFrom(List.class);
		
		field.getGenericType();*/
		
		clazz.getMethods();
		Method method = clazz.getDeclaredMethods()[1];
		
		Type parameterizedType = method.getParameters()[0].getParameterizedType();
	}
	
	
	public void test(List<Long> list){
		
	}

}
