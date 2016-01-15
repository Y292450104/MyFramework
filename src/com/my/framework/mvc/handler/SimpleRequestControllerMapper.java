package com.my.framework.mvc.handler;

import com.my.framework.mvc.servlet.ControllerWapper;
import com.my.framework.mvc.servlet.DispatcherContext;

public class SimpleRequestControllerMapper {
	
	public ControllerWapper mapper(String path) {
		return DispatcherContext.dispatcherContext().get(path);
	}
}
