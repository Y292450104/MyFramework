package com.my.framework.mvc.handler;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		HttpServletRequest httpServletRequest = (HttpServletRequest)FrameworkWebContext.getReqeust();
		HttpServletResponse HttpServletResponse = (HttpServletResponse)FrameworkWebContext.getResponse();
		System.out.println("DispatcherServlet service request url : " + httpServletRequest.getRequestURL());
		
		ControllerWapper controllerWapper = requestControllerMapper.mapper(httpServletRequest.getPathInfo());
		if (null != controllerWapper) {
			Object controller = ManagedBeanContext.currentContext()
				.getBean(controllerWapper.getControllerName());
			if (null != controller) {
				InvokerExecuter exectuer = new InvokerExecuter();
				Method method = controllerWapper.getMethod();
				exectuer.invoke(method, controller);
				return ;
			}
			
		}
	
		HttpServletResponse.sendError(404);	
	}
	
	void printServletRequestInfo() {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)FrameworkWebContext.getReqeust();
		System.out.println(httpServletRequest.getParameterMap());
		System.out.println(httpServletRequest.getDispatcherType());
		System.out.println(httpServletRequest.getServletContext());
		System.out.println(httpServletRequest.getServletContext().getContextPath());
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
		System.out.println(httpServletRequest.getRequestURL());
		System.out.println(httpServletRequest.getUserPrincipal());
		
		
		try {
			httpServletRequest.getParts();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ÎÄ¼þ´«Êä
		
	}
	
	protected boolean checkMethod(String methodType, String url) {
		return true;
	}

}
