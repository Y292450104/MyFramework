package com.my.framework.mvc.handler;

import javax.servlet.ServletRequest;

import com.my.framework.mvc.annotation.RequestParamParser;

public class RequestParamHander {
	private ServletRequest request = null;
	
	public RequestParamHander(ServletRequest request) {
		this.request = request;
	}

	/**
	 * 性能更好
	 * 
	 * @param bean
	 * @param request
	 */
	public void initBeanByRequestParam(Object bean) {
		RequestParamParser.parse(bean, request);
	}


	public <T> T initBeanByRequestParam(Class<T> clazz) {
		try {
			T object = clazz.newInstance();
			RequestParamParser.parse(object, request);
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
