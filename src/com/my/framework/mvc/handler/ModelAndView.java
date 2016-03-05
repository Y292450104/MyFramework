package com.my.framework.mvc.handler;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	private Map<String, Object> attributes = new HashMap<String, Object>();
	private String view = "";

	public ModelAndView() {

	}

	public ModelAndView(String view) {
		this.view = view;
	}

	public ModelAndView(String view, Map<String, Object> attributes) {
		this.view = view;
		this.attributes = attributes;
	}

	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

}
