package com.my.test.controller;

import com.my.framework.annotation.InjectResource;
import com.my.framework.mvc.annotation.Controller;
import com.my.framework.mvc.annotation.MappingPath;
import com.my.framework.mvc.handler.RequestParamHander;
import com.my.test.bean.BookBean;
import com.my.test.service.IBookService;

@Controller
@MappingPath("/book/")
public class BookController {
	@InjectResource(IBookService.SERVICE_NAME)
	private IBookService bookService;

	@MappingPath
	public void add() {
		// ManagedBeanWrapper wrapper =
		// ManagedBeanContext.currentContext().get(BookFacadeImpl.class.getName());
		// ((BookFacade)wrapper.getBean()).addBook();

		// ServletRequest request = FrameworkWebContext.getReqeust();
		// String bookId = request.getParameter("book_id");
		// String bookName = request.getParameter("book_name");
		// String bookPrice = request.getParameter("book_price");
		//
		// System.out.println("BookController add(), id:"+ bookId +",name:" +
		// bookName + ",price:" + bookPrice);
		// System.out.println(this.getClass());

		// BookBean book = new BookBean();
		// RequestParamHander.initBeanByRequestParam(book);
		BookBean book = RequestParamHander.initBeanByRequestParam(BookBean.class);
		System.out.println(book);
		bookService.addBook(book);
	}

	@MappingPath
	public void del() {
		System.out.println("BookController del");
	}
}
