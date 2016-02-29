package com.my.test.controller;

import com.my.framework.annotation.InjectResource;
import com.my.framework.mvc.annotation.Controller;
import com.my.framework.mvc.annotation.MappingPath;
import com.my.test.service.BookService;
import com.my.test.service.IBookService;

@Controller
@MappingPath("/book/")
public class BookController {
	@InjectResource(type=BookService.class)
	private IBookService bookService;
	
	@MappingPath
	public void add() {
		System.out.println("BookController add");
//		System.out.println(this.getClass());
//		ManagedBeanWrapper wrapper = ManagedBeanContext.currentContext().get(BookFacadeImpl.class.getName());
//		((BookFacade)wrapper.getBean()).addBook();
		bookService.addBook();
	}
	
	@MappingPath
	public void del() {
		System.out.println("BookController del");
	}
}
