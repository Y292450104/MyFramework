package com.my.framework.aop.proxy;

public class AopProxyFactory {
	
	public static AopProxy newDynamicProxy(Class<?> proxyType, AdvisedSupport advisedSupport) {
		System.out.println("proxy type:" + proxyType);
		return proxyType.isInterface() ? new JdkDynamicProxy(advisedSupport) : 
			//new CglibDynamicProxy();
			new CglibDynamicProxy(advisedSupport);
	}	
}



