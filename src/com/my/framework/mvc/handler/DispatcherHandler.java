package com.my.framework.mvc.handler;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.my.framework.core.InvokerExecuter;
import com.my.framework.init.ManagedBeanContext;
import com.my.framework.mvc.servlet.ControllerWapper;
import com.my.framework.mvc.servlet.FrameworkWebContext;
import com.my.framework.mvc.servlet.FrameworkWebContextUtils;

public class DispatcherHandler {
	public SimpleRequestControllerMapper requestControllerMapper = new SimpleRequestControllerMapper();
	
	public void service(ServletRequest request, ServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		FrameworkWebContextUtils.put(request, response);
		doService(request, response);
		FrameworkWebContextUtils.remove();
	}
	
	protected void doService(ServletRequest request, ServletResponse response) throws IOException {
		System.out.println("DispatcherServlet service");
		HttpServletRequest httpServletRequest = (HttpServletRequest)FrameworkWebContext.getReqeust();
		
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(httpServletRequest.getParameterMap());
		System.out.println(httpServletRequest.getDispatcherType());
		System.out.println(httpServletRequest.getServletContext());
		System.out.println(httpServletRequest.getServletContext().getVirtualServerName());
		System.out.println(httpServletRequest.getPathInfo());
		System.out.println(httpServletRequest.getContextPath());
		System.out.println(httpServletRequest.getRequestURI());
		System.out.println(httpServletRequest.getAuthType());
		System.out.println(httpServletRequest.getCharacterEncoding());
		System.out.println(httpServletRequest.getMethod());
		System.out.println(httpServletRequest.getPathTranslated());
		System.out.println(httpServletRequest.getProtocol());
		System.out.println(httpServletRequest.getQueryString());
		System.out.println(httpServletRequest.getRemoteAddr());
		System.out.println(httpServletRequest.getRemoteHost());
		System.out.println(httpServletRequest.getRemotePort());
		System.out.println(httpServletRequest.getRemoteUser());
		System.out.println(httpServletRequest.getRequestedSessionId());
		System.out.println(httpServletRequest.getRequestURI());
		System.out.println(httpServletRequest.getScheme());
		System.out.println(httpServletRequest.getServerName());
		System.out.println(httpServletRequest.getServerPort());
		System.out.println(httpServletRequest.getHeaderNames());
		System.out.println(httpServletRequest.getParameterMap());
		// System.out.println(httpServletRequest.getParts());
		// System.out.close();
		System.out.println(httpServletRequest.getReader());
		System.out.println(httpServletRequest.getRequestURL());
		System.out.println(httpServletRequest.getUserPrincipal());
		
		ControllerWapper controllerWapper = requestControllerMapper.mapper(httpServletRequest.getPathInfo());
		Object controller = ManagedBeanContext.currentContext()
				.get(controllerWapper.getControllerName())
				.getBean();
		InvokerExecuter exectuer = new InvokerExecuter();
		Method method = exectuer.method(controller.getClass(), controllerWapper.getMethodName(), controllerWapper.getMethodParameterTypes());
		
		exectuer.invoke(method, controller, 1);
		
		/*
		if ("/user.add".equals(httpServletRequest.getPathInfo())) {
			try {
				@SuppressWarnings("unchecked")
				Class<com.my.test.controller.UserController> clazz = (Class<UserController>) Thread.currentThread().getContextClassLoader().loadClass("com.my.test.controller.UserController");
				clazz.newInstance().add();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
	}
	
	
	protected boolean checkMethod(String methodType, String url) {
		return true;
	}

}
