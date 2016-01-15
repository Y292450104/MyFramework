package com.my.framework.mvc.servlet;

public class ControllerWapper {
	private String controllerName;
	private String methodName;
	private Class<?>[] methodParameterTypes;

	public ControllerWapper(String controllerName) {
		this(controllerName, null);
	}

	public ControllerWapper(String controllerName, String methodName, Class<?>... methodParameterTypes ) {
		this.controllerName = controllerName;
		this.methodName = methodName;
		this.methodParameterTypes = methodParameterTypes;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String toString() {
		return "{controllerName:" + controllerName + ",methodName:" + methodName 
				+ ",ControllerWapper:" + super.toString() + "}";
	}

	public Class<?>[] getMethodParameterTypes() {
		return methodParameterTypes;
	}

	public void setMethodParameterTypes(Class<?>[] methodParameterTypes) {
		this.methodParameterTypes = methodParameterTypes;
	}

}
