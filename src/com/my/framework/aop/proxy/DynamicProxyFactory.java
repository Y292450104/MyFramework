package com.my.framework.aop.proxy;

public class DynamicProxyFactory {
	public static DynamicProxy newDynamixProxy(Class<?> proxyType) {
		System.out.println("proxy type:" + proxyType);
		return proxyType.isInterface() ? new JdkDynamicProxy() : new CglibDynamicProxy();
	}	
}



