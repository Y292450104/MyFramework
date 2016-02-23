package com.my.framework.aop.proxy;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

public class AdvisedSupport {
	private TargetSource targetSource;
	private MethodInterceptor methodInterceptor;
	private MethodMatcher methodMatcher;
	private List<InterceptorAndMethodMatcher> interceptorsAndMethodMatchers;

	public TargetSource getTargetSource() {
		return targetSource;
	}

	public void setTargetSource(TargetSource targetSource) {
		this.targetSource = targetSource;
	}

	public MethodInterceptor getMethodInterceptor() {
		return methodInterceptor;
	}

	public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
		this.methodInterceptor = methodInterceptor;
	}

	public MethodMatcher getMethodMatcher() {
		return methodMatcher;
	}

	public void setMethodMatcher(MethodMatcher methodMatcher) {
		this.methodMatcher = methodMatcher;
	}

	public List<InterceptorAndMethodMatcher> getInterceptorsAndMethodMatchers() {
		return interceptorsAndMethodMatchers;
	}

	public void setInterceptorsAndMethodMatchers(List<InterceptorAndMethodMatcher> interceptorsAndMethodMatchers) {
		this.interceptorsAndMethodMatchers = interceptorsAndMethodMatchers;
	}
}
