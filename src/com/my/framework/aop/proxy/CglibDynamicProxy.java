package com.my.framework.aop.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/** 
 * cglib的动态代理实现
 *
 * cglib的动态代理无法设置状态的，即代理之后的返回的对象和传入的对象之间是没有状态的联系的。
 * 
 * 而jdk得动态代理是可以实现该功能的。由于cglib是负责
 *  
 * @author ynj 
 *  
 */  
public class CglibDynamicProxy implements MethodInterceptor, DynamicProxy {  
	//private Object target; 
  
    /** 
     * 创建代理对象 
     *  
     * @param target 
     * @return 
     */  
    public Object newProxyInstance(Class<?> instanceType) {  
        //this.target = target;  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(instanceType);  
        // 回调方法  
        enhancer.setCallback(this);  
        // 创建代理对象  
        return enhancer.create();
    }  
  
    @Override  
    // 回调方法  
    public Object intercept(Object obj, Method method, Object[] args,  
            MethodProxy proxy) throws Throwable {  
        System.out.println("事物开始  >>>>>>>>>>>>>>> by CglibDynamixProxy");  
        Object result = proxy.invokeSuper(obj, args);  
        System.out.println("事物结束 >>>>>>>>>>>>>>> by CglibDynamixProxy\n");
        return result;  
  
    }  
  
}  
