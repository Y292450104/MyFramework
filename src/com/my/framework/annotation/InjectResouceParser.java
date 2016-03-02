package com.my.framework.annotation;

import java.lang.reflect.Field;

import com.my.framework.init.ManagedBeanContext;


public class InjectResouceParser implements IAnnotationClassInstantiateParser {

	@Override
	public void parse(Object newInstance) {
		// TODO Auto-generated method stub
		Field[] fields = newInstance.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			InjectResource resource = field.getAnnotation(InjectResource.class);
			if (null == resource) {
				continue;
			}
			
			String beanName = resource.value();
			Object fieldValue = null;
			boolean hasValue = false;
			boolean fieldTypeIsInterface = field.getType().isInterface();
			
			if (!InjectResource.defaultValue.equals(beanName)) {
				if (fieldTypeIsInterface) {
					fieldValue = ManagedBeanContext.currentContext().getBean(beanName, field.getType());
				} else {
					fieldValue = ManagedBeanContext.currentContext().getBean(beanName);
				}
				
				hasValue = true;
			}
			
			Class<?> type = resource.type();
			if (!InjectResource.defaultType.equals(type) && !hasValue) {
				if (fieldTypeIsInterface) {
					fieldValue = ManagedBeanContext.currentContext().getBean(type, field.getType());
				} else {
					fieldValue = ManagedBeanContext.currentContext().getBean(type);
				}
				
				hasValue = true;
				
			} else if (!hasValue && fieldTypeIsInterface) {
				fieldValue = ManagedBeanContext.currentContext().getBean(field.getType());
				hasValue = true;
			}
			
			
			if (hasValue && null != fieldValue) {
				field.setAccessible(true);
				try {
					field.set(newInstance, fieldValue);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				throw new RuntimeException("FrameworkContext has not contains bean for class:" + newInstance.getClass() + " field:" + field.getName());
			}
		}
		
	}
	
}
