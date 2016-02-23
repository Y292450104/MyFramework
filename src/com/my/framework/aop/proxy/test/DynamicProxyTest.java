package com.my.framework.aop.proxy.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.my.framework.aop.proxy.AdvisedSupport;
import com.my.framework.aop.proxy.AopProxy;
import com.my.framework.aop.proxy.AopProxyFactory;
import com.my.framework.aop.proxy.AspectJExpressionPointcut;
import com.my.framework.aop.proxy.InterceptorAndMethodMatcher;
import com.my.framework.aop.proxy.TargetSource;

public class DynamicProxyTest {
	BookFacade bookFacade = null;
	BookFacadeImpl bookFacadeImpl = null;
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		String expression = "execution(* com.my.framework.aop.proxy.test.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        aspectJExpressionPointcut.setMethodInterceptor(new TimerInterceptor());
        List<InterceptorAndMethodMatcher> list = new ArrayList<>();
        list.add(aspectJExpressionPointcut);
        list.add(aspectJExpressionPointcut);
       
		
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
			advisedSupport.setInterceptorsAndMethodMatchers(list);
			AopProxy proxy = AopProxyFactory.newDynamicProxy(field.getType(), advisedSupport);
			field.set(test, proxy.getProxy());
			
			((BookFacade)proxy.getProxy()).addBook();
		}
		
		//test.bookFacade.addBook();
		//test.bookFacadeImpl.addBook();
	}
	
}
