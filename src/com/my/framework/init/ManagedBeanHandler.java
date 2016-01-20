package com.my.framework.init;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.my.framework.annotation.IAnnotationClassLoadParser;
import com.my.framework.annotation.ServiceParser;
import com.my.framework.mvc.annotation.ControllerParser;
import com.my.framework.mvc.servlet.ControllerWapper;
import com.my.framework.mvc.servlet.DispatcherContext;

public class ManagedBeanHandler {

	public ManagedBeanHandler() {
		// initManagedBeanMap();
	}

	@Test
	public void initManagedBeanContext() {
		System.out.println("ManagedBeanHandler.initManagedBeanMap()");
		// ManagedBeanContext.currentContext()..putClass("java.lang.Object",
		// Object.class);
		// ManagedBeanWrapper waper = new
		// ManagedBeanWrapper("java.lang.Object");
		// ManagedBeanContext.currentContext().put("java.lang.Object", waper);
		Set<Class<?>> classSet = ComponentScanHandler.getClasses("com.my");
		
		List<IAnnotationClassLoadParser> classLoadParserList = ManagedBeanContext.currentContext().classLoadParserList();
		classLoadParserList.add(new ControllerParser());
		classLoadParserList.add(new ServiceParser());
		
		System.out.println("classSet.size():" + classSet.size());
		for (Class<?> clazz : classSet) {
			for (IAnnotationClassLoadParser parser : ManagedBeanContext.currentContext().classLoadParserList()) {
				parser.parse(clazz);
			}
		}

		System.out.println("\n=================== beanNameWrapperMap =====================");

		Map<String, ManagedBeanWrapper> beanNameWrapperMap = ManagedBeanContext.currentContext().beanNameWrapperMap();
		for (Map.Entry<String, ManagedBeanWrapper> entry : beanNameWrapperMap.entrySet()) {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + entry.getKey() + ":" + entry.getValue());
		}

		System.out.println("\n==================== urlControllerMap ======================");
		System.out.println("urlControllerMap.size():" + DispatcherContext.dispatcherContext().urlControllerMap().size());
		System.out.println(DispatcherContext.dispatcherContext().urlControllerMap().toString());

		ControllerWapper controllerWapper = DispatcherContext.dispatcherContext().urlControllerMap().get("book/add");
		ManagedBeanWrapper managedBeanWrapper = ManagedBeanContext.currentContext()
				.get(controllerWapper.getControllerName());

		try {
			Object controller = managedBeanWrapper.getBean();
			controller.getClass().getMethod(controllerWapper.getMethodName()).invoke(managedBeanWrapper.getBean());
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

		classLoadParserList.clear();
	}

}
