package com.my.framework.init;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.my.framework.annotation.Controller;
import com.my.framework.annotation.Service;
import com.my.framework.mvc.annotation.MappingUrl;
import com.my.framework.mvc.servlet.ControllerWapper;
import com.my.framework.mvc.servlet.DispatcherContext;


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
			MappingUrl[] controllerUrlAnnos = clazz.getAnnotationsByType(MappingUrl.class);
			if (null != controllers && controllers.length != 0) {
				controllerClassList.add(clazz);
				ManagedBeanWrapper waper = new ManagedBeanWrapper(clazz.getName());
				ManagedBeanContext.currentContext().put(clazz.getName(), waper);
				
				String controllerUrl = "";
				if (null != controllerUrlAnnos && controllerUrlAnnos.length != 0) {
					controllerUrl = controllerUrlAnnos[0].value();
				}
				
				for (Method method : clazz.getMethods()) {
					MappingUrl methodUrlAnno = method.getAnnotation(MappingUrl.class);
					
					if (null != methodUrlAnno) {
						String mapperUrl = "";
						if (MappingUrl.methodNameAsDefaultUrl.equals(methodUrlAnno.value())) {
							mapperUrl = controllerUrl + method.getName();
						} else {
							mapperUrl = controllerUrl + methodUrlAnno.value();
						}
						
						ControllerWapper controllerWapper = new ControllerWapper(clazz.getName(), method.getName());
						DispatcherContext.dispatcherContext().put(mapperUrl, controllerWapper);
					}
				}
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
		
		System.out.println("\n==================== urlControllerMap ======================");
		System.out.println("urlControllerMap.size():" + DispatcherContext.dispatcherContext().urlControllerMap().size());
		System.out.println(DispatcherContext.dispatcherContext().urlControllerMap().toString());
	
		ControllerWapper controllerWapper = DispatcherContext.dispatcherContext().urlControllerMap().get("book/add");
		ManagedBeanWrapper managedBeanWrapper = ManagedBeanContext.currentContext().get(controllerWapper.getControllerName());
		
		try {
			managedBeanWrapper.clazz().getMethod(controllerWapper.getMethodName()).invoke(managedBeanWrapper.getBean());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
