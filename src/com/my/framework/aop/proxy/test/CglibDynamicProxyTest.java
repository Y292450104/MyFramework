package com.my.framework.aop.proxy.test;

import java.lang.reflect.Field;

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

		field.setAccessible(true);
		Object value = new JdkDynamicProxy()
				.newProxyInstance(BookFacadeImpl.class);
		field.set(testProxy, value);
		testProxy.bookProxy.addBook();
	}
}
