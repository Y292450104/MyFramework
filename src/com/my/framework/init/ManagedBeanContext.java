package com.my.framework.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.framework.annotation.IAnnotationClassInstantiateParser;
import com.my.framework.annotation.IAnnotationClassLoadParser;

public class ManagedBeanContext {
	private static ManagedBeanContext currentContext = new ManagedBeanContext();
	private Map<String, ManagedBeanWrapper> beanNameWrapperMap = new HashMap<>();
	private List<IAnnotationClassLoadParser> classLoadParserList = new ArrayList<>();
	private List<IAnnotationClassInstantiateParser> classInstantiateParser = new ArrayList<>();

	public void put(String beanName, ManagedBeanWrapper waper) {
		if (beanNameWrapperMap.containsKey(beanName)) {
			throw new RuntimeException("managedBeanContext init error: " + beanName + " has exist!");
		}
		beanNameWrapperMap.put(beanName, waper);
	}

	public ManagedBeanWrapper get(String beanName) {
		return beanNameWrapperMap.get(beanName);
	}

	public static ManagedBeanContext currentContext() {
		return currentContext;
	}
	
	public List<IAnnotationClassLoadParser> classLoadParserList() {
		return classLoadParserList;
	}
	
	public List<IAnnotationClassInstantiateParser> classInstantiateParser() {
		return classInstantiateParser;
	}
	
	public synchronized Map<String, ManagedBeanWrapper> beanNameWrapperMap() {
		return beanNameWrapperMap;
	}
}
