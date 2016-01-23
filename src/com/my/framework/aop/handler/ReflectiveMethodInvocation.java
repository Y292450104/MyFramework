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
		// currentInterceptorIndexĬ�ϵ���-1�ģ�����¼�ŵ�ǰִ�е����ĸ�������
//		if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers
//				.size() - 1) {
//			// ������е���������ִ�����˵Ļ��������invokeJoinpoint����ȥִ��Ŀ������Ŀ�귽��
//			return invokeJoinpoint();
//		}
//		// �õ���ǰҪִ�е�������(��������˳��ִ�еķ���ľ��=.=)
//		Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers
//				.get(++this.currentInterceptorIndex);
//		// �����жϵ�ǰ�������ǲ���һ����̬��������֮ǰ�н��� ���Ϸ�
//		if (interceptorOrInterceptionAdvice instanceof InterceptorAndDynamicMethodMatcher) {
//			InterceptorAndDynamicMethodMatcher dm = (InterceptorAndDynamicMethodMatcher) interceptorOrInterceptionAdvice;
//			// �������MethodMatcher���д�����������matches����
//			if (dm.methodMatcher.matches(this.method, this.targetClass,
//					this.arguments)) {
//				// ƥ��Ŀ�����Ŀ�귽����ִ�д�������
//				return dm.interceptor.invoke(this);
//			} else {
//				// �ݹ���ã���һ����������Ŀ����ķ���.
//				return proceed();
//			}
//		} else {
//			// ������������invoke��������this���ݹ�ȥ���������������еĴ���������Ƿ����ִ�е�Ȩ��
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
