package com.my.framework.aop.proxy.test;

import java.lang.reflect.Field;
import java.util.Random;

import com.my.framework.annotation.AspectParser;
import com.my.framework.aop.proxy.AdvisedSupport;
import com.my.framework.aop.proxy.AopProxy;
import com.my.framework.aop.proxy.AopProxyFactory;
import com.my.framework.aop.proxy.TargetSource;
import com.my.framework.init.ManagedBeanContext;

public class DynamicProxyTest {
	BookFacade bookFacade = null;
	BookFacadeImpl bookFacadeImpl = null;
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		//String expression = "execution(* com.my.framework.aop.proxy.test.*.*(..))";
        new AspectParser().parse(TimerInterceptor.class);
       
		
		DynamicProxyTest test = new DynamicProxyTest();
		Field[] fields = test.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			System.out.println("==============================");
			field.setAccessible(true);
			System.out.println("proxy class:" + field.getType());
			
			AdvisedSupport advisedSupport = new AdvisedSupport();
			BookFacadeImpl target = new BookFacadeImpl();
			target.id(new Random().nextInt());
			target.addBook();
			TargetSource targetSource = new TargetSource(target);
			advisedSupport.setTargetSource(targetSource);
			advisedSupport.setInterceptorsAndMethodMatchers(ManagedBeanContext.currentContext().interceptorAndMethodMatcherList());
			AopProxy proxy = AopProxyFactory.newDynamicProxy(field.getType(), advisedSupport);
			field.set(test, proxy.getProxy());
			
			((BookFacade)proxy.getProxy()).addBook();
		}
		
		//test.bookFacade.addBook();
		//test.bookFacadeImpl.addBook();
	}
	
}
