package com.my.framework.aop.proxy;

public class DynamicProxyFactory {
	public static DynamicProxy getDynamixProxy(Class<?> proxyClass) {
		System.out.println("field class:" + proxyClass);
		return proxyClass.isInterface() ? new JdkDynamicProxy() : new CglibDynamicProxy();
	}	
}
