package com.my.framework.mvc.servlet;

public class ControllerWapper {
	private String controllerName;
	private String methodName;

	public ControllerWapper(String controllerName) {
		this(controllerName, null);
	}

	public ControllerWapper(String controllerName, String methodName) {
		this.controllerName = controllerName;
		this.methodName = methodName;
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

}
