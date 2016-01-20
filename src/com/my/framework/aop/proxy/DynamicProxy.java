package com.my.framework.aop.proxy;

public interface DynamicProxy {
	
	 // 该方法在该对象的生命周期内每次调用返回的都是同一个实例 即一个代理对象只能服务于一个被代理的对象
	 public Object proxyInstance(Class<?> instanceType) throws InstantiationException, IllegalAccessException;
}
