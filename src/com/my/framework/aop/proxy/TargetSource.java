package com.my.framework.aop.proxy;

public class TargetSource {
	private Class<?> targetClass;
	private Class<?>[] interfaces;
	private Object target;
	
	public TargetSource(Object target) {
		this.target = target;
		this.targetClass = target.getClass();
		this.interfaces = target.getClass().getInterfaces();
	}
	
	public TargetSource(Object target, Class<?> targetClass,
			Class<?>... interfaces) {
		this.target = target;
		this.targetClass = targetClass;
		this.interfaces = interfaces;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public Object getTarget() {
		return target;
	}

	public Class<?>[] getInterfaces() {
		return interfaces;
	}
}
