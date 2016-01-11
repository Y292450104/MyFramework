package com.my.framework.mvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.my.framework.init.ManagedBeanContextUtils;

/**
 * servlet
 * @author ynj
 *
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("DispatcherServlet.init()");
		ManagedBeanContextUtils.init();
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		FrameworkWebContextUtils.dispatcherHandler().service(request, response);
	}
	
	
	
}
