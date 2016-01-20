package com.my.framework.utils;

import java.util.ResourceBundle;

public class StringUtils {

	public static boolean isNullOrEmpty(String str) {
		return null == str ? true : str.isEmpty();
	}
	
	public static boolean isNullOrSpace(String str) {
		return null == str ? true : "".equals(str.trim());
	}
	
	public static String toWithoutSpaceString(String str) {
		return null == str ? null : str.trim();
	}
	
	public static String toSqlLikeString(String str) {
		if (isNullOrSpace(str)) {
			return toWithoutSpaceString(str);
		}
		
		String s = str.trim();
		char[] chars = new char[s.length() + 2];
		s.getChars(0, s.length(), chars, 1);
		return chars.toString();
	}
	
	public static String getLocalValue(String key) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("");
			return bundle.getString(key);
		} catch (Exception e) {
			return key;
		}
	}
}
