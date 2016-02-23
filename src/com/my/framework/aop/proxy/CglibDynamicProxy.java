package com.my.framework.aop.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

import com.my.framework.aop.handler.ReflectiveMethodInvocation;

/**
 * @author yihua.huang@dianping.com
 */
public class CglibDynamicProxy extends AbstractAopProxy implements AopProxy{

	public CglibDynamicProxy(AdvisedSupport advised) {
		super(advised);
	}
	
	@Override
	public Object getProxy() {
		TargetSource targetSource = advised.getTargetSource();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetSource.getTarget().getClass());
		enhancer.setCallback(new DynamicAdvisedInterceptor(advised)); // 回调方法
		Object proxyInstance = enhancer.create(); // 创建代理对象
		return proxyInstance;
	}

	private static class DynamicAdvisedInterceptor implements MethodInterceptor {

		private AdvisedSupport advised;

		private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

		private DynamicAdvisedInterceptor(AdvisedSupport advised) {
			this.advised = advised;
			this.delegateMethodInterceptor = advised.getMethodInterceptor();
		}

		@Override
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			System.out.println(new Date() + " >>>>>>>>>>> Before invoke by CglibDynamixProxy");
			
			TargetSource targetSource = advised.getTargetSource();
			ReflectiveMethodInvocation reflectiveMethodInvocation = new ReflectiveMethodInvocation(targetSource.getTarget(),
					method, args, targetSource.getTargetClass(), advised.getInterceptorsAndMethodMatchers());
			
			if (advised.getMethodMatcher() != null
					&& advised.getMethodMatcher().matches(method, targetSource.getTargetClass())) {
				return delegateMethodInterceptor.invoke(reflectiveMethodInvocation);
			}
			return reflectiveMethodInvocation.proceed();
		}
	}

}
