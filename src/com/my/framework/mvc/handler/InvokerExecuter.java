package com.my.framework.mvc.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class InvokerExecuter {

	@SuppressWarnings("unused")
	private Method method(Class<?> clazz, String methodName,Class<?>... parameterTypes) {
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
		try {
			System.out.println(new Date() + " >>>>>>>>>>> before invoke InvokerExecuter!");
			method.invoke(instance, args);
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

		return null;
	}
}
