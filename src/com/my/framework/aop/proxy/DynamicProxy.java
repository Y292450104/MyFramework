package com.my.framework.aop.proxy;

public interface DynamicProxy {
	
	 // �÷����ڸö��������������ÿ�ε��÷��صĶ���ͬһ��ʵ�� ��һ���������ֻ�ܷ�����һ��������Ķ���
	 public Object proxyInstance(Class<?> instanceType) throws InstantiationException, IllegalAccessException;
}
