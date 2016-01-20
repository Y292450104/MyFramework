package com.my.framework.mvc.servlet;

import java.lang.reflect.Method;

public class ControllerWapper {
	private String controllerName;
	private Method method;

	public ControllerWapper(String controllerName) {
		this(controllerName, null);
	}

	public ControllerWapper(String controllerName, Method method) {
		this.controllerName = controllerName;
		this.method = method;
	}

	public String getControllerName() {
		return controllerName;
	}

	public String getMethodName() {
		return method.getName();
	}
	
	public Method getMethod() {
		return method;
	}

	public String toString() {
		return "{controllerName:" + controllerName + ",methodName:" + method.getName() 
				+ ",ControllerWapper:" + super.toString() + "}\n";
	}

}
