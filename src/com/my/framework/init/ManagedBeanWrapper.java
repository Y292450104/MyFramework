package com.my.framework.init;

import com.my.framework.aop.proxy.AdvisedSupport;
import com.my.framework.aop.proxy.AopProxyFactory;

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
	private AdvisedSupport advisedSupport;
	
	public ManagedBeanWrapper(Class<?> clazz) {
		this(clazz, false);
	}
	
	public ManagedBeanWrapper(Class<?> clazz, boolean isSingleton) {
		this.clazz = clazz;
		this.className = clazz.getName();
		if (isSingleton) {
			singletonBean = newInstance();
		}
	}
	
	private Object newInstance() {
		try {
			Object object = clazz.newInstance();
			if (null != advisedSupport) {
				object = proxy(object);
			}
			return object;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
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
			Object target = null;
			if (null != singletonBean) {
				target = singletonBean;
			}
			target = newInstance();
			return proxy(target);
	}
	
	private Object proxy(Object target) {
		return AopProxyFactory.newDynamicProxy(target.getClass(),advisedSupport).getProxy();
	}
	
	public Class<?> clazz() {
		return clazz;
	}
	
	public String toString() {
		return "className:" + className + " waper:" + super.toString();
	}

	public void setAdvisedSupport(AdvisedSupport advisedSupport) {
		this.advisedSupport = advisedSupport;
	}
}
