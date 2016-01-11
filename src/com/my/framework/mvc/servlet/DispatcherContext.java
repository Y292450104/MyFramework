package com.my.framework.mvc.servlet;

import java.util.HashMap;
import java.util.Map;

public class DispatcherContext {
	private static DispatcherContext dispatcherContext = new DispatcherContext();
	private Map<String, ControllerWapper> urlControllerMap = new HashMap<String, ControllerWapper>();
	
	public static DispatcherContext dispatcherContext() {
		return dispatcherContext;
	}
	
	public void put(String url, ControllerWapper controllerWapper) {
		if (urlControllerMap.containsKey(url)) {
			throw new RuntimeException("DispatcherContext Init Error! url:" + url + " has existed!");
		}
		urlControllerMap.put(url, controllerWapper);
	}
	
	public ControllerWapper get(String url) {
		return urlControllerMap.get(url);
	}
	
	public Map<String, ControllerWapper> urlControllerMap() {
		return urlControllerMap;
	}
}
