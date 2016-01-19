package com.my.framework.mvc.converter;

public interface Converter {
	Object getAsObject(String value, Object... param);
	String getAsString(Object value, Object... param);
}
