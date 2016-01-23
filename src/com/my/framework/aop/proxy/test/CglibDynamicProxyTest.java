package com.my.framework.aop.proxy.test;

import java.lang.reflect.Field;
import java.util.Random;

import com.my.framework.aop.proxy.*;

public class CglibDynamicProxyTest {
	BookFacadeImpl bookProxy = null;
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {
//		CglibDynamicProxy cglib = new CglibDynamicProxy();
//		BookFacadeImpl bookCglib = (BookFacadeImpl) cglib
//				.newProxyInstance(new BookFacadeImpl());
//		bookCglib.addBook();

		CglibDynamicProxyTest testProxy = new CglibDynamicProxyTest();
		Field field = testProxy.getClass().getDeclaredField("bookProxy");

		AdvisedSupport advisedSupport = new AdvisedSupport();
		BookFacadeImpl target = new BookFacadeImpl();
		target.id(new Random().nextInt());
		target.addBook();
		TargetSource targetSource = new TargetSource(target, null);
		advisedSupport.setTargetSource(targetSource);
		AopProxy proxy = new CglibDynamicProxy(advisedSupport);
		field.set(testProxy, proxy.getProxy());
		testProxy.bookProxy.addBook();
	}
}
