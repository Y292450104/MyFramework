package com.my.framework.aop.handler;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.Date;

import net.sf.cglib.proxy.MethodProxy;

import org.aopalliance.intercept.MethodInvocation;

public class ReflectiveMethodInvocation implements MethodInvocation {
	private Object target;
	private Method method;
    private Object[] arguments;
   
	public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
		this.target = target;
		this.method = method;
		this.arguments = arguments;
	}

	@Override
	public Method getMethod() {
		return method;
	}

	@Override
	public Object[] getArguments() {
		return arguments;
	}

	@Override
	public Object getThis() {
		return target;
	}

	@Override
	public AccessibleObject getStaticPart() {
		return method;
	}
	
	public Object proceed() throws Throwable {
		// currentInterceptorIndex默认等于-1的，它记录着当前执行到了哪个栏截器
//		if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers
//				.size() - 1) {
//			// 如果所有的拦截器都执行完了的话，则调用invokeJoinpoint方法去执行目标对象的目标方法
//			return invokeJoinpoint();
//		}
//		// 得到当前要执行的拦截器(拦截器是顺序执行的发现木有=.=)
//		Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers
//				.get(++this.currentInterceptorIndex);
//		// 下面判断当前拦截器是不是一个动态拦截器，之前有讲过 请上翻
//		if (interceptorOrInterceptionAdvice instanceof InterceptorAndDynamicMethodMatcher) {
//			InterceptorAndDynamicMethodMatcher dm = (InterceptorAndDynamicMethodMatcher) interceptorOrInterceptionAdvice;
//			// 这里调用MethodMatcher类中带三个参数的matches方法
//			if (dm.methodMatcher.matches(this.method, this.targetClass,
//					this.arguments)) {
//				// 匹配目标类的目标方法后执行此拦截器
//				return dm.interceptor.invoke(this);
//			} else {
//				// 递归调用，下一个拦截器或目标类的方法.
//				return proceed();
//			}
//		} else {
//			// 调用拦截器的invoke方法并将this传递过去，这样拦截器里中的代码就有了是否继续执行的权限
//			return ((MethodInterceptor) interceptorOrInterceptionAdvice)
//					.invoke(this);
//		}
		
		//return method.invoke(target, arguments);
		
		System.out.println(new Date() + " >>>>>>>>>>> class:" + target.getClass());
		System.out.println(new Date() + " >>>>>>>>>>> superClass:" + target.getClass().getSuperclass());
		System.out.println(new Date() + " >>>>>>>>>>> method:" + method.getName());
		System.out.println(new Date() + " >>>>>>>>>>> methodDeclaringClass:" + method.getDeclaringClass());
		
		
		return method.invoke(this.target, this.arguments);
	}
}
