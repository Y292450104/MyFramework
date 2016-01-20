package com.my.framework.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * Jdk的动态代理实现。
 * 
 * @author ynj
 * 
 */
public class JdkDynamicProxy implements InvocationHandler, DynamicProxy {
	private Object proxyInstance = null;
	private Object target = null;

	/**
	 * 绑定委托对象并返回一个代理类
	 * 
	 * @param target
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public synchronized Object proxyInstance(Class<?> instanceType) throws InstantiationException, IllegalAccessException {
		if (null == target) {
			target = instanceType.newInstance();
			proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(),
					target.getClass().getInterfaces(), this); // 要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
		}
		
		return proxyInstance;
	}

	@Override
	/** 
	 * 调用方法 
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// 增加aop切入点时间
		System.out.println(new Date() + " >>>>>>>>>>> Before invoke by JdkDynamixProxy");
		System.out.println(new Date() + " >>>>>>>>>>> class:" + proxy.getClass());
		System.out.println(new Date() + " >>>>>>>>>>> superClass:" + proxy.getClass().getSuperclass());
		System.out.println(new Date() + " >>>>>>>>>>> method:" + method.getName());
		System.out.println(new Date() + " >>>>>>>>>>> methodDeclaringClass:" + method.getDeclaringClass());
		System.out.println(new Date() + " >>>>>>>>>>> proxyInstanceClass:" + proxyInstance.getClass());
		System.out.println(new Date() + " >>>>>>>>>>> proxyInstanceSuperClass:" + proxyInstance.getClass().getSuperclass());
		// 执行方法
		Object result = method.invoke(target, args);
		
		// 添加aop切入点事件
		
		System.out.println(new Date() + " >>>>>>>>>> After invoke by JdkDynamixProxy\n");
		return result;
	}

}
