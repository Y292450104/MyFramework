/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.my.framework.mvc.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

/**
 * Implementation of {@link java.util.Map} for use when building model data for
 * use with UI tools. Supports chained calls and generation of model attribute
 * names.
 *
 * <p>
 * This class serves as generic model holder for both Servlet and Portlet MVC,
 * but is not tied to either of those. Check out the {@link Model} interface for
 * a Java-5-based interface variant that serves the same purpose.
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @since 2.0
 * @see Conventions#getVariableName
 * @see org.springframework.web.servlet.ModelAndView
 * @see org.springframework.web.portlet.ModelAndView
 */
@SuppressWarnings("serial")
public class ModelMap extends LinkedHashMap<String, Object> {

	private RequestParamHander requestParamHander;

	/**
	 * Construct a new, empty {@code ModelMap}.
	 */
	public ModelMap(HttpServletRequest request) {
		requestParamHander = new RequestParamHander(request);
	}

	/**
	 * Add the supplied attribute under the supplied name.
	 * 
	 * @param attributeName
	 *            the name of the model attribute (never {@code null})
	 * @param attributeValue
	 *            the model attribute value (can be {@code null})
	 */
	public ModelMap setAttribute(String attributeName, Object attributeValue) {
		Assert.assertNotNull(attributeName, "Model attribute name must not be null");
		put(attributeName, attributeValue);
		return this;
	}

	/**
	 * Copy all attributes in the supplied {@code Map} into this {@code Map}.
	 * 
	 * @see #addAttribute(String, Object)
	 */
	public ModelMap addAllAttributes(Map<String, ?> attributes) {
		if (attributes != null) {
			putAll(attributes);
		}
		return this;
	}

	/**
	 * Copy all attributes in the supplied {@code Map} into this {@code Map},
	 * with existing objects of the same name taking precedence (i.e. not
	 * getting replaced).
	 */
	public ModelMap mergeAttributes(Map<String, ?> attributes) {
		if (attributes != null) {
			for (Map.Entry<String, ?> entry : attributes.entrySet()) {
				String key = entry.getKey();
				if (!containsKey(key)) {
					put(key, entry.getValue());
				}
			}
		}
		return this;
	}

	/**
	 * Does this model contain an attribute of the given name?
	 * 
	 * @param attributeName
	 *            the name of the model attribute (never {@code null})
	 * @return whether this model contains a corresponding attribute
	 */
	public boolean containsAttribute(String attributeName) {
		return containsKey(attributeName);
	}

	public RequestParamHander requestParamHander() {
		return requestParamHander;
	}

}
