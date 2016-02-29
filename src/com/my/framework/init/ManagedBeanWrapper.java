package com.my.framework.init;

import com.my.framework.annotation.IAnnotationClassInstantiateParser;
import com.my.framework.aop.proxy.AdvisedSupport;
import com.my.framework.aop.proxy.AopProxyFactory;
import com.my.framework.aop.proxy.TargetSource;

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
			singletonBean = newInstance(clazz);
		}
	}
	
	private Object newInstance(Class<?> interfaceType) {
		try {
			Object target = clazz.newInstance();
			initTarget(target);
			if (null != advisedSupport) {
				advisedSupport.setTargetSource(new TargetSource(target));
				target = proxy(target, interfaceType);
			}
			return target;
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
	
	private void initTarget(Object target) {
		for (IAnnotationClassInstantiateParser parser : ManagedBeanContext.currentContext().classInstantiateParserList()) {
			parser.parse(target);
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
	
	public synchronized Object getBean() {
		Object target = null;
		if (null != singletonBean) {
			target = singletonBean;
		}
		target = newInstance(this.clazz);
		System.out.println(target.getClass());
		return target;
	}
	
	public synchronized Object getBean(Class<?> interfaceType) {
		Object target = null;
		if (null != singletonBean) {
			target = singletonBean;
		}
		target = newInstance(interfaceType);
		System.out.println(target.getClass());
		return target;
	}
	
	private Object proxy(Object target, Class<?> interfaceType) {
		return AopProxyFactory.newDynamicProxy(interfaceType,advisedSupport).getProxy();
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
	
	public AdvisedSupport getAdvisedSupport() {
		return advisedSupport;
	}
}
