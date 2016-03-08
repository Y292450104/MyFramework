package com.my.framework.mvc.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InvokerExecuter {

	public ModelMap getModelMapByArgs(Object[] args) {
		for (Object param : args) {
			if (param instanceof ModelMap)
				return (ModelMap) param;
		}
		return null;
	}

	public Object[] args(Method method, HttpServletRequest request, HttpServletResponse response) {
		Object[] args = new Object[method.getParameterCount()];

		int index = 0;
		for (Parameter param : method.getParameters()) {
			if (HttpServletRequest.class.isAssignableFrom(param.getType())) {
				args[index++] = request;
				continue;
			}

			if (HttpServletResponse.class.isAssignableFrom(param.getType())) {
				args[index++] = response;
				continue;
			}

			if (ModelMap.class.isAssignableFrom(param.getType())) {
				args[index++] = new ModelMap(request);
				continue;
			}

			throw new RuntimeException(
					"Controller:" + method.getDeclaringClass() + " Method:" + method + " has not support paramType");
		}
		return args;
	}

	@SuppressWarnings("unused")
	private Method method(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
		try {
			return clazz.getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object invoke(Method method, Object instance, Object... args) {
		// Method method = clazz.getMethod(methodName);
		Object result = null;
		try {
			System.out.println(new Date() + " >>>>>>>>>>> before invoke InvokerExecuter!");
			result = method.invoke(instance, args);
			System.out.println(new Date() + " >>>>>>>>>>> After invoke InvokerExecuter!");

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
