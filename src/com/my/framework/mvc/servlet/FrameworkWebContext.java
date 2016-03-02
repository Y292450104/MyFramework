package com.my.framework.mvc.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FrameworkWebContext {
	private static Map<Long, ServletRequest> requestMap = new HashMap<Long, ServletRequest>();
	private static Map<Long, ServletResponse> responseMap = new HashMap<Long, ServletResponse>();

	static void putRequest(Long id, ServletRequest request) {
		synchronized (requestMap) {
			requestMap.put(id, request);
		}
	}

	static void removeRequest(Long id) {
		synchronized (requestMap) {
			requestMap.remove(id);
		}
	}

	public static ServletRequest getReqeust() {
		// System.out.println("requestMap:" + requestMap);
		synchronized (requestMap) {
			return requestMap.get(Thread.currentThread().getId());
		}
	}

	static void putResponse(Long id, ServletResponse response) {
		synchronized (responseMap) {
			responseMap.put(id, response);
		}
	}

	static void removeResponse(Long id) {
		synchronized (responseMap) {
			responseMap.remove(id);
		}
	}

	public static ServletResponse getResponse() {
		synchronized (responseMap) {
			// System.out.println("responseMap:" + responseMap);
			return responseMap.get(Thread.currentThread().getId());
		}
	}
	
}
