package com.my.framework.mvc.annotation;

import java.lang.reflect.Method;

import com.my.framework.annotation.IAnnotationClassLoadParser;
import com.my.framework.init.ManagedBeanContext;
import com.my.framework.init.ManagedBeanWrapper;
import com.my.framework.mvc.servlet.ControllerWapper;
import com.my.framework.mvc.servlet.DispatcherContext;

public class ControllerParser implements IAnnotationClassLoadParser{

	@Override
	public void parse(Class<?> clazz) {
		// TODO Auto-generated method stub
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + clazz.getName());
		
		Controller[] controllers = clazz.getAnnotationsByType(Controller.class);
		MappingPath[] controllerUrlAnnos = clazz.getAnnotationsByType(MappingPath.class);
		if (null != controllers && controllers.length != 0) {
			ManagedBeanWrapper waper = new ManagedBeanWrapper(clazz);
			ManagedBeanContext.currentContext().put(clazz.getName(), waper);
			
			String controllerUrl = "";
			if (null != controllerUrlAnnos && controllerUrlAnnos.length != 0) {
				controllerUrl = controllerUrlAnnos[0].value();
			}
			
			for (Method method : clazz.getMethods()) {
				MappingPath methodUrlAnno = method.getAnnotation(MappingPath.class);
				
				if (null != methodUrlAnno) {
					String mapperUrl = "";
					if (MappingPath.methodNameAsDefaultPath.equals(methodUrlAnno.value())) {
						mapperUrl = controllerUrl + method.getName();
					} else {
						mapperUrl = controllerUrl + methodUrlAnno.value();
					}
					
					ControllerWapper controllerWapper = new ControllerWapper(clazz.getName(), method);
					DispatcherContext.dispatcherContext().put(mapperUrl, controllerWapper);
				}
			}
		}
	}
	
}
