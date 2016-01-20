package com.my.framework.aop.proxy;

import java.lang.reflect.Method;
import java.util.Date;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib�Ķ�̬����ʵ��
 *
 * cglib�Ķ�̬�����޷�����״̬�ģ�������֮��ķ��صĶ���ʹ���Ķ���֮����û��״̬����ϵ�ġ�
 * 
 * ��jdk�ö�̬�����ǿ���ʵ�ָù��ܵġ�����cglib�Ǹ���
 * 
 * @author ynj
 * 
 */
public class CglibDynamicProxy implements MethodInterceptor, DynamicProxy {
	private Object proxyInstance;

	/**
	 * �����������
	 * 
	 * @param target
	 * @return
	 */
	public synchronized Object proxyInstance(Class<?> instanceType) {
		// this.target = target;
		if (null == proxyInstance) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(instanceType);
			enhancer.setCallback(this); // �ص�����
			proxyInstance = enhancer.create(); // �����������
		}
		
		return proxyInstance;
	}

	@Override
	// �ص�����
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
