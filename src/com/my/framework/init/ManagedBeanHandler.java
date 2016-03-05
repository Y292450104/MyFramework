package com.my.framework.init;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.my.framework.annotation.AspectParser;
import com.my.framework.annotation.IAnnotationClassInstantiateParser;
import com.my.framework.annotation.IAnnotationClassLoadParser;
import com.my.framework.annotation.InjectResouceParser;
import com.my.framework.annotation.ServiceParser;
import com.my.framework.aop.proxy.AdvisedSupport;
import com.my.framework.aop.proxy.InterceptorAndMethodMatcher;
import com.my.framework.mvc.annotation.ControllerParser;
import com.my.framework.mvc.servlet.ControllerWapper;
import com.my.framework.mvc.servlet.DispatcherContext;

public class ManagedBeanHandler {

	@Test
	public void initManagedBeanContext() {
		System.out.println("ManagedBeanHandler.initManagedBeanMap()");
		Set<Class<?>> classSet = ComponentScanHandler.getClasses("com.my");
		initClassLoadParserList();

		System.out.println("classSet.size():" + classSet.size());
		for (Class<?> clazz : classSet) { // 类初始化解析器
			for (IAnnotationClassLoadParser parser : ManagedBeanContext.currentContext().classLoadParserList()) {
				parser.parse(clazz);
			}
		}

		System.out.println("\n=================== beanNameWrapperMap =====================");
		Map<String, ManagedBeanWrapper> beanNameWrapperMap = ManagedBeanContext.currentContext().beanNameWrapperMap();
		for (Map.Entry<String, ManagedBeanWrapper> entry : beanNameWrapperMap.entrySet()) {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + entry.getKey() + ":" + entry.getValue());
		}

		System.out.println("\n==================== urlControllerMap ======================");
		System.out
				.println("urlControllerMap.size():" + DispatcherContext.dispatcherContext().urlControllerMap().size());
		System.out.println(DispatcherContext.dispatcherContext().urlControllerMap().toString());

		clearClassLoadParserList();
		initClassInstanceParserList();
		initManagedBeanAdvicedSupport();

		testController();
	}

	private void initManagedBeanAdvicedSupport() {
		// TODO Auto-generated method stub
		System.out.println("\n=============== initManagedBeanAdvicedSupport ==================");
		for (Map.Entry<String, ManagedBeanWrapper> entry : ManagedBeanContext.currentContext().beanNameWrapperMap()
				.entrySet()) {
			ManagedBeanWrapper wrapper = entry.getValue();
			List<InterceptorAndMethodMatcher> list = ManagedBeanContext.currentContext()
					.interceptorAndMethodMatcherListByTargetClass(wrapper.clazz());
			if (!list.isEmpty()) {
				System.out.println("wrapper:" + wrapper.getClassName() + " matcher.size:" + list.size());
				AdvisedSupport advisedSupport = new AdvisedSupport();
				advisedSupport.setInterceptorsAndMethodMatchers(list);
				wrapper.setAdvisedSupport(advisedSupport);
			}
		}
		System.out.println("");
	}

	private void initClassLoadParserList() {
		// TODO Auto-generated method stub
		List<IAnnotationClassLoadParser> classLoadParserList = ManagedBeanContext.currentContext()
				.classLoadParserList();
		classLoadParserList.add(new ControllerParser());
		classLoadParserList.add(new ServiceParser());
		classLoadParserList.add(new AspectParser());
	}

	private void initClassInstanceParserList() {
		List<IAnnotationClassInstantiateParser> classInstantiateParserList = ManagedBeanContext.currentContext()
				.classInstantiateParserList();
		classInstantiateParserList.add(new InjectResouceParser());
	}

	private void clearClassLoadParserList() {
		ManagedBeanContext.currentContext().classLoadParserList().clear();
	}

	private void testController() {
		ControllerWapper controllerWapper = DispatcherContext.dispatcherContext().urlControllerMap().get("/book/add");
		if (null == controllerWapper) {
			return;
		}

		Object controller = ManagedBeanContext.currentContext().getBean(controllerWapper.getControllerName());

		try {
			controller.getClass().getMethod(controllerWapper.getMethodName()).invoke(controller);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
