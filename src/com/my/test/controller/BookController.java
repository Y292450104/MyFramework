package com.my.test.controller;

import com.my.framework.annotation.Controller;
import com.my.framework.mvc.annotation.MappingPath;

@Controller
@MappingPath("book/")
public class BookController {
	
	@MappingPath
	public void add() {
		System.out.println("BookController add");
	}
	
	@MappingPath
	public void del() {
		System.out.println("BookController del");
	}
}
