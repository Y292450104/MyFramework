package com.my.framework.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * Jdk�Ķ�̬����ʵ�֡�
 * 
 * @author ynj
 * 
 */
public class JdkDynamicProxy implements InvocationHandler, DynamicProxy {
	private Object proxyInstance = null;
	private Object target = null;

	/**
	 * ��ί�ж��󲢷���һ��������
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
					target.getClass().getInterfaces(), this); // Ҫ�󶨽ӿ�(����һ��ȱ�ݣ�cglib�ֲ�����һȱ��)
		}
		
		return proxyInstance;
	}

	@Override
	/** 
	 * ���÷��� 
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// ����aop�����ʱ��
		System.out.println(new Date() + " >>>>>>>>>>> Before invoke by JdkDynamixProxy");
		System.out.println(new Date() + " >>>>>>>>>>> class:" + proxy.getClass());
		System.out.println(new Date() + " >>>>>>>>>>> superClass:" + proxy.getClass().getSuperclass());
		System.out.println(new Date() + " >>>>>>>>>>> method:" + method.getName());
		System.out.println(new Date() + " >>>>>>>>>>> methodDeclaringClass:" + method.getDeclaringClass());
		System.out.println(new Date() + " >>>>>>>>>>> proxyInstanceClass:" + proxyInstance.getClass());
		System.out.println(new Date() + " >>>>>>>>>>> proxyInstanceSuperClass:" + proxyInstance.getClass().getSuperclass());
		// ִ�з���
		Object result = method.invoke(target, args);
		
		// ���aop������¼�
		
		System.out.println(new Date() + " >>>>>>>>>> After invoke by JdkDynamixProxy\n");
		return result;
	}

}
