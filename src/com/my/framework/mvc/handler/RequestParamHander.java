package com.my.framework.mvc.handler;

import com.my.framework.mvc.annotation.RequestParamParser;

public class RequestParamHander {

	public static void initBeanByRequestParam(Object bean) {
		RequestParamParser.initQueryBeanByRequestParam(bean);
	}

	public static <T> T initBeanByRequestParam(Class<T> clazz) {
		try {
			T object = clazz.newInstance();
			RequestParamParser.initQueryBeanByRequestParam(clazz.newInstance());
			return object;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
