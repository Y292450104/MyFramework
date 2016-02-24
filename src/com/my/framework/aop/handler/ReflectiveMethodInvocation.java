package com.my.framework.aop.handler;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.my.framework.aop.proxy.InterceptorAndMethodMatcher;

/**
 * @author computer
 * ����ʹ�����±��ʶ�˵�ǰ�������ĸ��������У����Ըö����ڵ���ʱ�����̰߳�ȫ�ġ�
 * ��ʹ�ñ���һ�������ʵ�����Ժ��ʹ�ÿ�¡����ɣ�
 * 
 * ��ȻҲ����ʹ��proceed�Ĳ��������ݡ�
 * 
 * �����ģʽΪ���͵�������ģʽ��ͨfilter��filterChain�� servlet֮��Ĺ�ϵ��
 */
public class ReflectiveMethodInvocation implements MethodInvocation, Cloneable {
	private int currentInterceptorIndex = -1;
	private Object target;
	private Method method;
    private Object[] arguments;
    private final Class<?> targetClass;
    private List<InterceptorAndMethodMatcher> interceptorsAndMethodMatchers = new ArrayList<>();
    
	public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments
			, Class<?> targetClass, List<InterceptorAndMethodMatcher> interceptorsAndMethodMatchers) {
		this.target = target;
		this.method = method;
		this.targetClass = targetClass;
		this.arguments = arguments;
		this.interceptorsAndMethodMatchers = interceptorsAndMethodMatchers;
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
		
		if (currentInterceptorIndex == interceptorsAndMethodMatchers.size() - 1) {
			return this.invoke();
		}
		
		InterceptorAndMethodMatcher interceptorAndMethodMatcher = interceptorsAndMethodMatchers.get(++currentInterceptorIndex);
		if (interceptorAndMethodMatcher.matches(method, targetClass)) {
			// ����������;
			return interceptorAndMethodMatcher.invoke(this);
		}  else {
			return proceed();
		}
		
	}
	
	private Object invoke() throws Throwable{
		System.out.println(new Date() + " >>>>>>>>>>> class:" + target.getClass());
//		System.out.println(new Date() + " >>>>>>>>>>> superClass:" + target.getClass().getSuperclass());
		System.out.println(new Date() + " >>>>>>>>>>> method:" + method.getName());
//		System.out.println(new Date() + " >>>>>>>>>>> methodDeclaringClass:" + method.getDeclaringClass());
		Object result = method.invoke(this.target, this.arguments);
//		System.out.println(new Date() + " >>>>>>>>>>> end:" + target.getClass());
		return result;
	}
}
