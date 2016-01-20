package com.my.framework.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Jdk�Ķ�̬����ʵ�֡�
 * 
 * @author ynj
 * 
 */
public class JdkDynamicProxy implements InvocationHandler, DynamicProxy {

	private Object target;

	/**
	 * ��ί�ж��󲢷���һ��������
	 * 
	 * @param target
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Object newProxyInstance(Class<?> instanceType) throws InstantiationException, IllegalAccessException {
		target = instanceType.newInstance();
		// ȡ�ô������
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this); // Ҫ�󶨽ӿ�(����һ��ȱ�ݣ�cglib�ֲ�����һȱ��)
	}

	@Override
	/** 
	 * ���÷��� 
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		System.out.println("���￪ʼ  >>>>>>>>>>>>>>> by JdkDynamixProxy");
		
		// ����aop�����ʱ��
		
		// ִ�з���
		result = method.invoke(target, args);
		
		// ���aop������¼�
		
		System.out.println("�������  >>>>>>>>>>>>>>> by JdkDynamixProxy");
		return result;
	}

}
