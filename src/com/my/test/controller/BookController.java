package com.my.test.controller;

import javax.servlet.http.HttpServletRequest;

import com.my.framework.annotation.InjectResource;
import com.my.framework.mvc.annotation.Controller;
import com.my.framework.mvc.annotation.MappingPath;
import com.my.framework.mvc.handler.ModelAndView;
import com.my.framework.mvc.handler.ModelMap;
import com.my.framework.mvc.handler.RequestParamHander;
import com.my.framework.mvc.servlet.FrameworkWebContext;
import com.my.test.bean.BookBean;
import com.my.test.service.IBookService;

/**
 * 
 * @author Administrator
 *
 * @input: http://localhost:8080/MyFramework/book/add.do?book_id=1&book_name=2&
 *         book_price=1.5
 */

@Controller
@MappingPath("/book/")
public class BookController {
	@InjectResource(IBookService.SERVICE_NAME)
	private IBookService bookService;

	@MappingPath("add.do")
	public ModelAndView add(ModelMap modelMap) {
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
		BookBean book = new RequestParamHander(FrameworkWebContext.getReqeust()).initBeanByRequestParam(BookBean.class);
		System.out.println(book);
		bookService.addBook(book);

		book = modelMap.requestParamHander().initBeanByRequestParam(BookBean.class);
		System.out.println(book);
		bookService.addBook(book);

		ModelAndView mv = new ModelAndView("/book_add_response.jsp");
		mv.setAttribute("book", book);
		mv.setAttribute("message", "add success");
		return mv;
	}

	@MappingPath
	public String del(ModelMap modelMap) {
		System.out.println("BookController del");
		BookBean book = modelMap.requestParamHander().initBeanByRequestParam(BookBean.class);
		System.out.println(book);
		bookService.addBook(book);

		modelMap.setAttribute("book", book);
		modelMap.setAttribute("message", "del success");
		return "/book_add_response.jsp";
	}

	@MappingPath
	public String get(HttpServletRequest request, ModelMap modelMap) {
		System.out.println("BookController del");
		BookBean book = new RequestParamHander(request).initBeanByRequestParam(BookBean.class);
		System.out.println(book);
		bookService.addBook(book);

		modelMap.setAttribute("book", book);
		modelMap.setAttribute("message", "get success");
		return "/book_add_response.jsp";
	}
}
