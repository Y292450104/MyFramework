package com.my.framework.aop.handler;

public class ReflectiveMethodInvocation {
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
		
		return new Object();
	}
}
