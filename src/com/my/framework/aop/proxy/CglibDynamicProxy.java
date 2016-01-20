package com.my.framework.aop.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/** 
 * cglib�Ķ�̬����ʵ��
 *
 * cglib�Ķ�̬�����޷�����״̬�ģ�������֮��ķ��صĶ���ʹ���Ķ���֮����û��״̬����ϵ�ġ�
 * 
 * ��jdk�ö�̬�����ǿ���ʵ�ָù��ܵġ�����cglib�Ǹ���
 *  
 * @author ynj 
 *  
 */  
public class CglibDynamicProxy implements MethodInterceptor, DynamicProxy {  
	//private Object target; 
  
    /** 
     * ����������� 
     *  
     * @param target 
     * @return 
     */  
    public Object newProxyInstance(Class<?> instanceType) {  
        //this.target = target;  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(instanceType);  
        // �ص�����  
        enhancer.setCallback(this);  
        // �����������  
        return enhancer.create();
    }  
  
    @Override  
    // �ص�����  
    public Object intercept(Object obj, Method method, Object[] args,  
            MethodProxy proxy) throws Throwable {  
        System.out.println("���￪ʼ  >>>>>>>>>>>>>>> by CglibDynamixProxy");  
        Object result = proxy.invokeSuper(obj, args);  
        System.out.println("������� >>>>>>>>>>>>>>> by CglibDynamixProxy\n");
        return result;  
  
    }  
  
}  
