package com.my.framework.init;

import java.util.HashMap;
import java.util.Map;

public class ManagedBeanContext {
	private static ManagedBeanContext currentContext = new ManagedBeanContext();
	private Map<String, ManagedBeanWrapper> managedBeanMap = new HashMap<>();
	
	public void put(String beanName, ManagedBeanWrapper waper) {
		if (managedBeanMap.containsKey(beanName)) {
			throw new RuntimeException("managedBeanContext init error: " + beanName + " has exist, ");
		}
		managedBeanMap.put(beanName, waper);
	}
	
	public ManagedBeanWrapper get(String beanName) {
		return managedBeanMap.get(beanName);
	}
	
	public static synchronized ManagedBeanContext currentContext() {
		return currentContext;
	}
}
