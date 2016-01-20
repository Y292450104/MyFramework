package com.my.framework.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CommonUtils {
	
	public static boolean isNullorEmpty(List<?> list) {
		return null == list ? true : list.isEmpty();
	}
	
	
	public static Double formatDoubleValue(int newScale, Double value) {
		if (null == value) {
			return null;
		}
		
		BigDecimal bd = new BigDecimal(value);
		return bd.setScale(newScale, RoundingMode.HALF_UP).doubleValue();
	}
}
