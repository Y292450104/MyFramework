package com.my.framework.annotation;

/**
 * 类加载时调用
 * @author Administrator
 *
 */
public interface IAnnotationClassLoadParser {
	void parse(Class<?> clazz);
}
