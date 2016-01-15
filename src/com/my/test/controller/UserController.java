package com.my.test.controller;

import com.my.framework.mvc.annotation.Controller;
import com.my.framework.mvc.annotation.MappingPath;

@Controller
@MappingPath("/user/")
public class UserController {

	@MappingPath
	public void add() {
		System.out.println("BookController add");
	}
	
	@MappingPath("addById")
	public void add(int id) {
		System.out.println("BookController addid:" + id);
	}
}
