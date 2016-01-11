package com.my.framework.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingUrl {
	public static final String methodNameAsDefaultUrl = "methodNameAsDefaultUrl";
	public String value() default methodNameAsDefaultUrl;
}
