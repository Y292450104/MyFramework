package com.my.framework.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Jdk的动态代理实现。
 * 
 * @author ynj
 * 
 */
public class JdkDynamicProxy implements InvocationHandler, DynamicProxy {

	private Object target;

	/**
	 * 绑定委托对象并返回一个代理类
	 * 
	 * @param target
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Object newProxyInstance(Class<?> instanceType) throws InstantiationException, IllegalAccessException {
		target = instanceType.newInstance();
		// 取得代理对象
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this); // 要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
	}

	@Override
	/** 
	 * 调用方法 
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		System.out.println("事物开始  >>>>>>>>>>>>>>> by JdkDynamixProxy");
		
		// 增加aop切入点时间
		
		// 执行方法
		result = method.invoke(target, args);
		
		// 添加aop切入点事件
		
		System.out.println("事物结束  >>>>>>>>>>>>>>> by JdkDynamixProxy");
		return result;
	}

}
