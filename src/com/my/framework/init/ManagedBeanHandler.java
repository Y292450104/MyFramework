package com.my.framework.init;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.my.framework.annotation.Controller;
import com.my.framework.annotation.Service;


public class ManagedBeanHandler {
	
	public ManagedBeanHandler() {
		//initManagedBeanMap();
	}
	
	@Test
	public void initManagedBeanContext() {
		System.out.println("ManagedBeanHandler.initManagedBeanMap()");
		//ManagedBeanContext.currentContext()..putClass("java.lang.Object", Object.class);
		//ManagedBeanWrapper waper = new ManagedBeanWrapper("java.lang.Object");
		//ManagedBeanContext.currentContext().put("java.lang.Object", waper);
		Set<Class<?>> classSet = ComponentScanHandler.getClasses("com.my");
		
		List<Class<?>> controllerClassList = new LinkedList<>();
		List<Class<?>> serviceClassList = new LinkedList<>();
		
		
		System.out.println("classSet.size():" + classSet.size());
		for (Class<?> clazz : classSet) {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + clazz.getName());
			
			Controller[] controllers = clazz.getAnnotationsByType(Controller.class);
			if (null != controllers && controllers.length != 0) {
				controllerClassList.add(clazz);
				ManagedBeanWrapper waper = new ManagedBeanWrapper(clazz.getName());
				ManagedBeanContext.currentContext().put(clazz.getName(), waper);
			}
			
			Service[] services = clazz.getAnnotationsByType(Service.class);
			if (null != services && services.length != 0) {
				ManagedBeanWrapper waper = new ManagedBeanWrapper(clazz.getName());
				ManagedBeanContext.currentContext().put(clazz.getName(), waper);
				serviceClassList.add(clazz);
			}
		}
		
		System.out.println("\n======================== controller =======================");
		for (Class<?> clazz : controllerClassList) {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + ManagedBeanContext
					.currentContext().get(clazz.getName()));
		}
		
		System.out.println("\n========================== service ========================");
		for (Class<?> clazz : serviceClassList) {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + ManagedBeanContext
					.currentContext().get(clazz.getName()));
		}
	}

}
