package com.my.framework.aop.proxy.test;

import java.lang.reflect.Field;
import java.util.Random;

import com.my.framework.aop.proxy.AdvisedSupport;
import com.my.framework.aop.proxy.AopProxy;
import com.my.framework.aop.proxy.JdkDynamicProxy;
import com.my.framework.aop.proxy.TargetSource;

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
		AdvisedSupport advisedSupport = new AdvisedSupport();
		BookFacadeImpl target = new BookFacadeImpl();
		target.id(new Random().nextInt());
		target.addBook();
		TargetSource targetSource = new TargetSource(target, null);
		advisedSupport.setTargetSource(targetSource);
		AopProxy proxy = new JdkDynamicProxy(advisedSupport);
		field.set(testProxy, proxy.getProxy());
		testProxy.bookProxy.addBook();
		
    }  
}
