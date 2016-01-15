package com.my.framework.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingPath {
	public static final String methodNameAsDefaultPath = "methodNameAsDefaultPath";
	public String value() default methodNameAsDefaultPath;
}
