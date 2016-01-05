package com.my.framework.init;

import java.util.HashMap;
import java.util.Map;

public class ManagedBeanContext {
	private static Map<String, Class<?>> managedBeanNameClassMap = new HashMap<String, Class<?>>();
	private static Map<String, Object> singletonBeanNameInstanceMap = new HashMap<String, Object>();
	
	public static void putClass(String className, Class<?> clazz) {
		managedBeanNameClassMap.put(className, clazz);
	}
	
	public static Class<?> getClass(String className) {
		 Class<?> clazz = managedBeanNameClassMap.get(className);
		if (null == clazz) {
			try {
				throw new ClassNotFoundException("Not found class :" + className);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e.getMessage(), e);
			}
			
		}
		return null;
	}
	
	public static void putSingletionInstance(String className, Object object) {
		singletonBeanNameInstanceMap.put(className, object);
	}
	
	public static Object getSingletionInstance(String className) {
		Object object = singletonBeanNameInstanceMap.get(className);
		if (null == object) {
			try {
				object = getClass(className).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			putSingletionInstance(className, object);
		}
		
		return object;
	}
}
