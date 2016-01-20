package com.my.framework.aop.proxy;

public interface DynamicProxy {
	
	 // 该方法在该对象的生命周期内只应该被调用一次 即一个代理对象只能服务于一个被代理的对象
	 public Object newProxyInstance(Class<?> instanceType) throws InstantiationException, IllegalAccessException;
}
