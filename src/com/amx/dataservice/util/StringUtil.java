package com.amx.dataservice.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {
	
	/**
	 * 将逗号分割的ID字符串转换为ID列表
	 * @param idsStr
	 * @return
	 */
	public static List<Integer> explodeIdStr2IdList(String idsStr){
		List<Integer> list = null;
		if(idsStr != null && idsStr.length() != 0){
			list = new ArrayList<Integer>();
			for(String idStr : idsStr.split(",")){
				list.add(Integer.valueOf(idStr));
			}
		}
			
		return list;
	}
	
	public static List<Long> explodeIdStr2LongIdList(String idsStr){
		List<Long> list = null;
		if(idsStr != null && idsStr.length() != 0){
			list = new ArrayList<Long>();
			for(String idStr : idsStr.split(",")){
				list.add(Long.valueOf(idStr));
			}
		}
		
		return list;
	}
	
	/**
	 * 将字符串列表转换为逗号分割的字符串
	 * @param list
	 * @return
	 */
	public static String mergeList2Str(List<String> list){
		StringBuilder sb = null;
		if(list != null && list.size() != 0){
			sb = new StringBuilder();
			for (String str : list) {
				sb.append(str);
				//逗号分割
				sb.append(",");
			}
		}
		
		if(sb != null && sb.length() != 0){
			return sb.substring(0, sb.length() - 1);
		}
		
		return null;
	}
	
	/**
	 * 将逗号分割的字符串转换为字符串列表
	 * @param Str
	 * @return
	 */
	public static List<String> explodeStr2List(String Str){
		
		if(Str != null){
			return Arrays.asList(Str.split(","));
		}
		
		return null;
	}

	public static boolean isEmpty(String str) {
		if(str != null && !"".equals(str.trim())){
			return false;
		}
		
		return true;
	}
}
