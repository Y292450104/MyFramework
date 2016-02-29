package com.my.framework.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface InjectResource {
	public final static String defaultValue = "";
	public final static Class<?> defaultType = Object.class;
	
	public String value() default defaultValue;
	public Class<?> type() default Object.class;
}
