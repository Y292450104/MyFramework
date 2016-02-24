package com.my.test.controller;

import com.my.framework.aop.proxy.test.BookFacade;
import com.my.framework.aop.proxy.test.BookFacadeImpl;
import com.my.framework.init.ManagedBeanContext;
import com.my.framework.init.ManagedBeanWrapper;
import com.my.framework.mvc.annotation.Controller;
import com.my.framework.mvc.annotation.MappingPath;

@Controller
@MappingPath("/book/")
public class BookController {
	
	@MappingPath
	public void add() {
		System.out.println("BookController add");
		System.out.println(this.getClass());
		ManagedBeanWrapper wrapper = ManagedBeanContext.currentContext().get(BookFacadeImpl.class.getName());
		((BookFacade)wrapper.getBean()).addBook();
	}
	
	@MappingPath
	public void del() {
		System.out.println("BookController del");
	}
}
