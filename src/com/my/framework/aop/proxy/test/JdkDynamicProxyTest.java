package com.my.framework.aop.proxy.test;

import java.lang.reflect.Field;

import com.my.framework.aop.proxy.JdkDynamicProxy;

public class JdkDynamicProxyTest {
	BookFacade bookProxy = null;
	
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {  
        //JdkDynamicProxy proxy = new JdkDynamicProxy();  
		
		//BookFacadeImpl book = new BookFacadeImpl();
        //BookFacade bookProxy = (BookFacade) (new JdkDynamicProxy().newProxyInstance(book)); 
        //bookProxy.addBook(); 
		
		JdkDynamicProxyTest testProxy = new JdkDynamicProxyTest();
		
		Field field = testProxy.getClass().getDeclaredField("bookProxy");
		
		field.setAccessible(true);
		Object value = new JdkDynamicProxy().newProxyInstance(BookFacadeImpl.class);
		field.set(testProxy, value);
		testProxy.bookProxy.addBook();
		
    }  
}
