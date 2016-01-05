package com.my.framework.init;

import java.util.Set;

import org.junit.Test;

import com.my.framework.annotation.Controller;


public class ManagedBeanHandler {
	
	public ManagedBeanHandler() {
		//initManagedBeanMap();
	}
	
	@Test
	public void initManagedBeanContext() {
		System.out.println("ManagedBeanHandler.initManagedBeanMap()");
		ManagedBeanContext.putClass("java.lang.Object", Object.class);
		Set<Class<?>> classSet = ComponentScanHandler.getClassSet("com.my.test.controller");
		try {
			classSet.add(Thread.currentThread().getContextClassLoader().loadClass("com.my.test.controller.UserController"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("classSet.size():" + classSet.size());
		for (Class<?> clazz : classSet) {
			System.out.println(clazz.getName());
			
			Controller[] controllers = clazz.getAnnotationsByType(Controller.class);
			
			if (null != controllers && controllers.length != 0) {
				System.out.println("\n===============================================================");
				System.out.println("Controller : " + clazz.getName());
				System.out.println("===============================================================\n");
			}
			
			
//			Annotation[] annotations = clazz.getAnnotations();
//			if (null != annotations) {
//				for (Annotation annotation : annotations) {
//					if (annotation.annotationType().equals(Controller.class)) {
//						System.out.println("\n===============================================================");
//						System.out.println("Controller : " + clazz.getName());
//						System.out.println("===============================================================\n");
//					}
//				}
//			}
		}
	}

}
