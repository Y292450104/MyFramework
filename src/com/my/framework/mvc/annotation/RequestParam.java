package com.my.framework.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
	public static final String fieldNameAsDefaultRequestParamName = "fieldNameAsDefaultRequestParamName";
	public String value() default fieldNameAsDefaultRequestParamName;
	public boolean apply() default true;
}
