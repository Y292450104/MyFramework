package com.my.test.service;

import java.util.Date;

import com.my.framework.annotation.Service;

@Service
public class BookService implements IBookService{

	public void addBook() {
		// TODO Auto-generated method stub
		System.out.println(new Date() + ">>>>>>>>>>>>>>>>>>>>>>> BookService.addBook()");
	}

}
