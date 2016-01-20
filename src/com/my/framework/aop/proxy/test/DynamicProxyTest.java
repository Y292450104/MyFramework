package com.my.framework.aop.proxy.test;

import java.lang.reflect.Field;

import com.my.framework.aop.proxy.DynamicProxy;
import com.my.framework.aop.proxy.DynamicProxyFactory;

public class DynamicProxyTest {
	BookFacade bookFacade = null;
	BookFacadeImpl bookFacadeImpl = null;
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		DynamicProxyTest test = new DynamicProxyTest();
		Field[] fields = test.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			field.setAccessible(true);
			DynamicProxy proxy = DynamicProxyFactory.newDynamixProxy(field.getType());
			System.out.println("proxy class:" + proxy.getClass());
			
			field.set(test, proxy.proxyInstance(BookFacadeImpl.class));
		}
		
		test.bookFacade.addBook();
		test.bookFacadeImpl.addBook();
	}
	
}
