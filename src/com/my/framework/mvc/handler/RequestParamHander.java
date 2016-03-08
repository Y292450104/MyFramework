package com.my.framework.mvc.handler;

import javax.servlet.http.HttpServletRequest;

import com.my.framework.mvc.annotation.RequestParamParser;

public class RequestParamHander {

	public static void initBeanByRequestParam(Object bean) {
		RequestParamParser.parse(bean);
	}

	/**
	 * 性能更好
	 * 
	 * @param bean
	 * @param request
	 */
	public static void initBeanByRequestParam(Object bean, HttpServletRequest request) {
		RequestParamParser.parse(bean, request);
	}

	public static <T> T initBeanByRequestParam(Class<T> clazz) {
		return _initBeanByRequestParam(clazz, null);
	}

	/**
	 * 性能更好
	 * 
	 * @param bean
	 * @param request
	 */
	public static <T> T initBeanByRequestParam(Class<T> clazz, HttpServletRequest request) {
		return _initBeanByRequestParam(clazz, request);
	}

	private static <T> T _initBeanByRequestParam(Class<T> clazz, HttpServletRequest request) {
		try {
			T object = clazz.newInstance();
			if (null == request) {
				RequestParamParser.parse(object);
			} else {
				RequestParamParser.parse(object, request);
			}
			return object;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				throw new RuntimeException().initCause(e);
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
}
