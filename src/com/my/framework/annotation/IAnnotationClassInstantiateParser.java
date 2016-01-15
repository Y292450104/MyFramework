package com.my.framework.annotation;

/*
 * 类实例化时调用
 */
public interface IAnnotationClassInstantiateParser {
	void parse(Object newInstance);
}
