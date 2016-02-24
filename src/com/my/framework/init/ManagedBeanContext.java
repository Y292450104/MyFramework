package com.my.framework.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.framework.annotation.IAnnotationClassInstantiateParser;
import com.my.framework.annotation.IAnnotationClassLoadParser;
import com.my.framework.aop.proxy.AdvisedSupport;
import com.my.framework.aop.proxy.AspectJExpressionPointcut;
import com.my.framework.aop.proxy.InterceptorAndMethodMatcher;

public class ManagedBeanContext {
	private static ManagedBeanContext currentContext = new ManagedBeanContext();
	private Map<String, ManagedBeanWrapper> beanNameWrapperMap = new HashMap<>();
	private List<IAnnotationClassLoadParser> classLoadParserList = new ArrayList<>();
	private List<IAnnotationClassInstantiateParser> classInstantiateParserList = new ArrayList<>();
	private List<InterceptorAndMethodMatcher> interceptorAndMethodMatcherList = new ArrayList<>();
	
	public void put(String beanName, ManagedBeanWrapper waper) {
		if (beanNameWrapperMap.containsKey(beanName)) {
			throw new RuntimeException("managedBeanContext init error: " + beanName + " has exist!");
		}
		beanNameWrapperMap.put(beanName, waper);
	}

	public ManagedBeanWrapper get(String beanName) {
		ManagedBeanWrapper wrapper = beanNameWrapperMap.get(beanName);
		return wrapper;
	}

	public static ManagedBeanContext currentContext() {
		return currentContext;
	}
	
	public List<IAnnotationClassLoadParser> classLoadParserList() {
		return classLoadParserList;
	}
	
	public List<IAnnotationClassInstantiateParser> classInstantiateParserList() {
		return classInstantiateParserList;
	}
	
	public synchronized Map<String, ManagedBeanWrapper> beanNameWrapperMap() {
		return beanNameWrapperMap;
	}
	
	public List<InterceptorAndMethodMatcher> interceptorAndMethodMatcherList() {
		return interceptorAndMethodMatcherList;
	}
	
	public List<InterceptorAndMethodMatcher> interceptorAndMethodMatcherListByTargetClass(Class<?> targetClass) {
		List<InterceptorAndMethodMatcher> returnList  = new ArrayList<InterceptorAndMethodMatcher>();
		for (InterceptorAndMethodMatcher matcher : interceptorAndMethodMatcherList) {
			if (matcher instanceof AspectJExpressionPointcut 
					&& ((AspectJExpressionPointcut)matcher).matches(targetClass)) {
				returnList.add(matcher);
			}
		}
		
		return returnList;
	}

}
