package com.my.framework.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.annotation.Aspect;

import com.my.framework.aop.proxy.AspectJExpressionPointcut;
import com.my.framework.init.ManagedBeanContext;

public class AspectParser implements IAnnotationClassLoadParser{

	@Override
	public void parse(Class<?> clazz) {
		// TODO Auto-generated method stub
		if (null == clazz.getAnnotation(Aspect.class)) {
			return ;
		}
		
		if (MethodInterceptor.class.isAssignableFrom(clazz)) {
			System.out.println("Class:" + clazz + " is MethodInterceptor");
			
			String expression = clazz.getAnnotation(Aspect.class).value();
			AspectJExpressionPointcut aspectJ = new AspectJExpressionPointcut();
			aspectJ.setExpression(expression);
			
			try {
				aspectJ.setMethodInterceptor((MethodInterceptor) clazz.newInstance());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			}
			
			ManagedBeanContext.currentContext().interceptorAndMethodMatcherList().add(aspectJ);
			
		}
	}
	
}
