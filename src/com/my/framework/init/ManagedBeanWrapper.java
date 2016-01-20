package com.my.framework.init;

import com.my.framework.aop.proxy.DynamicProxyFactory;

/**
 * 
 * @author Administrator
 * 
 * 用于map中的value， key为bean的name
 */
public class ManagedBeanWrapper {
	private String className;
	@SuppressWarnings("unused")
	private String beanName;
	private Class<?> clazz;
	private static Object singletonBean;
	@SuppressWarnings("unused")
	private String type;
	
	public ManagedBeanWrapper(Class<?> clazz) {
		this(clazz, false);
	}
	
	public ManagedBeanWrapper(Class<?> clazz, boolean isSingleton) {
		try {
			this.clazz = clazz;
			this.className = clazz.getName();
			if (isSingleton) {
				singletonBean = newProxyInstance();
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getClassName() {
		return className;
	}
	
	public Object getSingletonBean() {
		if (null == singletonBean) {
			throw new RuntimeException(className + " is not a singleton bean!");
		}
		return singletonBean;
	}
	
	public Object getBean() {
		try {
			if (null != singletonBean) {
				return singletonBean;
			}
			return newProxyInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("", e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("", e);
		}
	}
	
	private Object newProxyInstance() throws InstantiationException, IllegalAccessException {
		return DynamicProxyFactory.newDynamixProxy(clazz).proxyInstance(clazz);
	}
	
	public Class<?> clazz() {
		return clazz;
	}
	
	public String toString() {
		return "className:" + className + " waper:" + super.toString();
	}
}
