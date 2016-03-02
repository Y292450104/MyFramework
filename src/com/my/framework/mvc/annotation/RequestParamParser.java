package com.my.framework.mvc.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.my.framework.annotation.IAnnotationClassInstantiateParser;
import com.my.framework.mvc.servlet.FrameworkWebContext;

public class RequestParamParser implements IAnnotationClassInstantiateParser{

	@Override
	public void parse(Object newInstance) {
		// TODO Auto-generated method stub
		initQueryBeanByRequestParam(newInstance);
	}

	
	public static void initQueryBeanByRequestParam(Object bean) {
		Class<?> clazz = bean.getClass();

		boolean classParamApply = false;
		RequestParam classParam = clazz.getAnnotation(RequestParam.class);
		if (null != classParam && !classParam.apply()) {
			return;
		}

		if (null != classParam && classParam.apply()) {
			classParamApply = true;
		}

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			int modifiers = field.getModifiers();
			if (Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers)) {
				continue;
			}

			boolean fieldParamApply = false;
			RequestParam fieldParam = field.getAnnotation(RequestParam.class);
			String paramName = null;

			if (null != fieldParam) {
				fieldParamApply = fieldParam.apply();
				paramName = fieldParam.value();
				if (RequestParam.fieldNameAsDefaultRequestParamName
						.equals(paramName)) {
					paramName = field.getName();
				}
			} else if (classParamApply) {
				fieldParamApply = true;
				paramName = field.getName();
			}

			if (fieldParamApply) {
				String paramValue = FrameworkWebContext.getReqeust().getParameter(paramName);
				try {
					Object object = parseParamByApplyType(field.getType(), paramValue);
					System.out.println("fieldName:" + field.getName() + ", fieldType:" + field.getType() 
						+ ", paramName:" + paramName + ", paramValue:" + paramValue + " fieldValueAtferParam:" + object);
				
					if (null != object) {
						field.setAccessible(true);
						field.set(bean, object);
						// field.set(queryBean, paramValue);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	private static Object parseParamByApplyType(Class<?> fieldType,
			String paramValue) {
		if (null == paramValue || "".equals(paramValue.trim())) {
			return null;
		}

		if (fieldType.equals(String.class)) {
			return paramValue;
		}

		if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
			return new Integer(paramValue);
		}
		if (fieldType.equals(double.class) || fieldType.equals(Double.class)) {
			return new Double(paramValue);
		}
		if (fieldType.equals(float.class) || fieldType.equals(Float.class)) {
			return new Float(paramValue);
		}
		if (fieldType.equals(long.class) || fieldType.equals(Long.class)) {
			return new Long(paramValue);
		}
		if (fieldType.equals(short.class) || fieldType.equals(Short.class)) {
			return new Short(paramValue);
		}
		if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)) {
			return new Boolean(paramValue);
		}
		if (fieldType.equals(byte.class) || fieldType.equals(Byte.class)) {
			return new Byte(paramValue);
		}
		if (fieldType.equals(char.class) || fieldType.equals(Character.class)) {
			char[] chars = paramValue.toCharArray();
			if (null != chars && chars.length == 1) {
				return chars[0];
			}
			return null;
		}

		System.out.println("parseStringToApplyType error: fieldType:"
				+ fieldType + " parse error!");
		return null;
	}
}
