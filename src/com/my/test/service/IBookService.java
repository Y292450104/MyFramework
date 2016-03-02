package com.my.test.service;

import com.my.test.bean.BookBean;

public interface IBookService {
	public static final String SERVICE_NAME = "com.my.test.service.BookService";
	public void addBook(BookBean book);
}
