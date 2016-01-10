package com.my.framework.init;

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
	
	public ManagedBeanWrapper(String className) {
		this(className, false);
	}
	
	public ManagedBeanWrapper(String className, boolean isSingleton) {
		try {
			this.className = className;
			clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
			singletonBean = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
			return clazz.newInstance();
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
	
	public String toString() {
		return "className:" + className + " waper:" + super.toString();
	}
}
