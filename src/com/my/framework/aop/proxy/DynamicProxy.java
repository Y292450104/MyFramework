package com.my.framework.aop.proxy;

public interface DynamicProxy {
	
	 // �÷����ڸö��������������ֻӦ�ñ�����һ�� ��һ���������ֻ�ܷ�����һ��������Ķ���
	 public Object newProxyInstance(Class<?> instanceType) throws InstantiationException, IllegalAccessException;
}
