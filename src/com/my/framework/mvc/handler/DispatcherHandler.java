package com.my.framework.mvc.handler;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

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
	private SimpleRequestControllerMapper requestControllerMapper = new SimpleRequestControllerMapper();
	private InvokerExecuter executer = new InvokerExecuter();

	public void service(ServletRequest request, ServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		FrameworkWebContextUtils.put(request, response);
		doService(request, response);
		FrameworkWebContextUtils.remove();
	}

	protected void doService(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		System.out.println("DispatcherServlet service request url : " + httpServletRequest.getRequestURL());
		System.out.println("DispatcherServlet service MAPPING_PATH : " + getMappingPath(httpServletRequest));

		ControllerWapper controllerWapper = requestControllerMapper.mapper(getMappingPath(httpServletRequest));
		if (null != controllerWapper) {
			Object controller = ManagedBeanContext.currentContext().getBean(controllerWapper.getControllerName());
			if (null != controller) {
				Method method = controllerWapper.getMethod();
				Object[] args = executer.args(method, httpServletRequest, httpServletResponse);
				Object result = executer.invoke(method, controller, args);

				boolean dispatchSuccess = dispatchViewAndAnalyzeResponseModel(httpServletRequest, httpServletResponse,
						result, executer.getModelMapByArgs(args));
				if (dispatchSuccess) {
					return;
				}
			}

		}
		// printServletRequestInfo();
		httpServletResponse.sendError(404);
	}

	protected String getMappingPath(HttpServletRequest httpServletRequest) {
		// httpServletRequest
		String contextPath = httpServletRequest.getContextPath();
		String RequestURI = httpServletRequest.getRequestURI();
		System.out.println(httpServletRequest.getContextPath());
		System.out.println(httpServletRequest.getRequestURI());
		return RequestURI.replace(contextPath, "");
	}

	protected boolean dispatchViewAndAnalyzeResponseModel(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object result, ModelMap modelMap) {
		if (null != modelMap) {
			for (Map.Entry<String, Object> entry : modelMap.entrySet()) {
				httpServletRequest.setAttribute(entry.getKey(), entry.getValue());
			}
		}

		if (result instanceof String) {
			String responsePath = (String) result;
			if (responsePath.trim().equals("")) {
				responsePath = httpServletRequest.getPathInfo();
			}
			dispatch(httpServletRequest, httpServletResponse, responsePath);
			return true;
		}

		if (result instanceof ModelAndView) {
			ModelAndView mv = (ModelAndView) result;
			String responsePath = mv.getView();
			if (null == responsePath || responsePath.trim().equals("")) {
				responsePath = httpServletRequest.getPathInfo();
			}

			Map<String, Object> models = mv.getAttributes();
			for (Map.Entry<String, Object> entry : models.entrySet()) {
				httpServletRequest.setAttribute(entry.getKey(), entry.getValue());
			}
			dispatch(httpServletRequest, httpServletResponse, responsePath);
			return true;
		}
		return false;
	}

	protected void dispatch(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String responsePath) {
		try {
			System.out.println(new Date() + " >>>>>>>>>>>>>>>>> responsePath:" + responsePath);
			httpServletRequest.getRequestDispatcher(responsePath).forward(httpServletRequest, httpServletResponse);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ========================= TEST ================
	void printServletRequestInfo() {

		HttpServletRequest httpServletRequest = (HttpServletRequest) FrameworkWebContext.getReqeust();
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
