package com.my.framework.annotation;

import com.my.framework.init.ManagedBeanContext;
import com.my.framework.init.ManagedBeanWrapper;


public class ServiceParser implements IAnnotationClassLoadParser {

	@Override
	public void parse(Class<?> clazz) {
		// TODO Auto-generated method stub
		Service[] services = clazz.getAnnotationsByType(Service.class);
		if (null != services && services.length != 0) {
			ManagedBeanWrapper waper = new ManagedBeanWrapper(clazz.getName());
			ManagedBeanContext.currentContext().put(clazz.getName(), waper);
		}
	}

}
