package com.my.framework.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

import com.my.framework.aop.handler.ReflectiveMethodInvocation;

/**
 * Jdk�Ķ�̬����ʵ�֡�
 * 
 * @author ynj
 * 
 */
public class JdkDynamicProxy extends AbstractAopProxy implements AopProxy {

	public JdkDynamicProxy(AdvisedSupport advised) {
		super(advised);
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ί�ж��󲢷���һ��������
	 * 
	 * @param target
	 * @return
	 * */
	@Override
	public Object getProxy() {
		// TODO Auto-generated method stub
		TargetSource targetSource = advised.getTargetSource();
		Object target = targetSource.getTarget();
		Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), new DynamicAdvisedInterceptor(advised)); // Ҫ�󶨽ӿ�(����һ��ȱ�ݣ�cglib�ֲ�����һȱ��)
		return proxyInstance;
	}

	/** 
	 * ���÷��� 
	 */
	protected Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// ����aop�����ʱ��
		System.out.println(new Date() + " >>>>>>>>>>> Before invoke by JdkDynamixProxy");
		System.out.println(new Date() + " >>>>>>>>>>> class:" + proxy.getClass());
		System.out.println(new Date() + " >>>>>>>>>>> superClass:" + proxy.getClass().getSuperclass());
		System.out.println(new Date() + " >>>>>>>>>>> method:" + method.getName());
		System.out.println(new Date() + " >>>>>>>>>>> methodDeclaringClass:" + method.getDeclaringClass());
		// ִ�з���
		// ���aop������¼�
		//Object result = new ReflectiveMethodInvocation(targetSource.getTarget(), method, args).proceed();
		
		System.out.println(new Date() + " >>>>>>>>>> After invoke by JdkDynamixProxy\n");
		return null;
	}

	
	private static class DynamicAdvisedInterceptor implements InvocationHandler {

		private AdvisedSupport advised;

		private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

		private DynamicAdvisedInterceptor(AdvisedSupport advised) {
			this.advised = advised;
			this.delegateMethodInterceptor = advised.getMethodInterceptor();
		}


		@Override
		public Object invoke(Object target, Method method, Object[] param)
				throws Throwable {
			// TODO Auto-generated method stub
			System.out.println(new Date() + " >>>>>>>>>>> Before invoke by JdkDynamixProxy");
			if (advised.getMethodMatcher() != null
					&& advised.getMethodMatcher().matches(method, advised.getTargetSource().getTargetClass())) {
				return delegateMethodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, param));
			}
			return new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, param).proceed();
		
		}

	}

}
