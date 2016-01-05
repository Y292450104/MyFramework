package com.my.framework.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FrameworkWebContext {
	private static Map<Long, ServletRequest> requestMap = new HashMap<Long, ServletRequest>();
	private static Map<Long, ServletResponse> responseMap = new HashMap<Long, ServletResponse>();
	
	
	static void putRequest(Long id, ServletRequest request) {
		requestMap.put(id, request);
	}
	
	static void removeRequest(Long id) {
		requestMap.remove(id);
	}

	public static ServletRequest getReqeust() {
		System.out.println("requestMap:" + requestMap);
		return requestMap.get(Thread.currentThread().getId());
	}
	
	static void putResponse(Long id, ServletResponse response) {
		responseMap.put(id, response);
	}
	
	static void removeResponse(Long id) {
		responseMap.remove(id);
	}

	public static ServletResponse getResponse() {
		System.out.println("responseMap:" + responseMap);
		return responseMap.get(Thread.currentThread().getId());
	}
}
