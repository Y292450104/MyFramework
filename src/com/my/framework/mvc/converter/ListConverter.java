package com.my.framework.mvc.converter;

import java.util.ArrayList;
import java.util.List;

public class ListConverter implements Converter {

	@Override
	public Object getAsObject(String value, Object... param) {
		// TODO Auto-generated method stub
		if (null != value) {
			List<String> list = new ArrayList<String>();
			String[] strs = value.split(",");
			
			for (String str : strs) {
				list.add(str);
			}
			return list;
		}
		
		return null;
	}

	@Override
	public String getAsString(Object value, Object... param) {
		// TODO Auto-generated method stub
		if (null != value && value instanceof List) {
			String strList = value.toString();
			return strList.substring(1, strList.length() - 1);
		}

		return null;
	}

}
