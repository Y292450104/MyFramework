package com.my.framework.aop.proxy;

import java.lang.reflect.Method;
import java.util.Date;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib的动态代理实现
 *
 * cglib的动态代理无法设置状态的，即代理之后的返回的对象和传入的对象之间是没有状态的联系的。
 * 
 * 而jdk得动态代理是可以实现该功能的。由于cglib是负责
 * 
 * @author ynj
 * 
 */
public class CglibDynamicProxy implements MethodInterceptor, DynamicProxy {
	private Object proxyInstance;

	/**
	 * 创建代理对象
	 * 
	 * @param target
	 * @return
	 */
	public synchronized Object proxyInstance(Class<?> instanceType) {
		// this.target = target;
		if (null == proxyInstance) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(instanceType);
			enhancer.setCallback(this); // 回调方法
			proxyInstance = enhancer.create(); // 创建代理对象
		}
		
		return proxyInstance;
	}

	@Override
	// 回调方法
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {

		System.out.println(new Date() + " >>>>>>>>>>> Before invoke by CglibDynamixProxy");

		System.out.println(new Date() + " >>>>>>>>>>> class:" + obj.getClass());
		System.out.println(new Date() + " >>>>>>>>>>> superClass:" + obj.getClass().getSuperclass());
		System.out.println(new Date() + " >>>>>>>>>>> method:" + method.getName());
		System.out.println(new Date() + " >>>>>>>>>>> methodDeclaringClass:" + method.getDeclaringClass());
		Object result = proxy.invokeSuper(obj, args);
		System.out.println(new Date() + " >>>>>>>>>>> After invoke by CglibDynamixProxy\n");
		return result;

	}

}
