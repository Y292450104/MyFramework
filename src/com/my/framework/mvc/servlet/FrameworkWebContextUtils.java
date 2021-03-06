package com.my.framework.mvc.servlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.my.framework.mvc.handler.DispatcherHandler;

public class FrameworkWebContextUtils {
	private static DispatcherHandler dispatcherHandler = new DispatcherHandler();
	
	public static void put(ServletRequest request, ServletResponse response) {
		Long id = Thread.currentThread().getId();
		FrameworkWebContext.putRequest(id, request);
		FrameworkWebContext.putResponse(id, response);
	}
	
	public static void remove() {
		Long id = Thread.currentThread().getId();
		FrameworkWebContext.removeRequest(id);
		FrameworkWebContext.removeResponse(id);
	}
	
	public static DispatcherHandler dispatcherHandler() {
		return dispatcherHandler;
	}
}
