package com.my.test.service;

import java.util.Date;

import com.my.framework.annotation.Service;
import com.my.test.bean.BookBean;

@Service
public class BookService implements IBookService{

	public void addBook(BookBean book) {
		// TODO Auto-generated method stub
		System.out.println(new Date() + ">>>>>>>>>>>>>>>>>>>>>>> BookService.addBook()");
		System.out.println(new Date() + ">>>>>>>>>>>>>>>>>>>>>>> BookService.addBook:" + book);
	}

}
