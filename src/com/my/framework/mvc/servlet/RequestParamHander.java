package com.my.framework.mvc.servlet;

import com.my.framework.mvc.annotation.RequestParamParser;

public class RequestParamHander {
	
	public void initQueryBeanByRequestParam(Object queryBean) {
		new RequestParamParser().parse(queryBean);
	}
}
