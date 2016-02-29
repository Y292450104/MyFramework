package com.my.test.controller;

import com.my.framework.annotation.InjectResource;
import com.my.framework.mvc.annotation.Controller;
import com.my.framework.mvc.annotation.MappingPath;
import com.my.test.service.UserService;

@Controller
@MappingPath("/user/")
public class UserController {
	@InjectResource
	private UserService userService;

	@MappingPath
	public void add() {
		System.out.println("BookController add");
		userService.add();
	}
	
	@MappingPath("addById")
	public void add(int id) {
		System.out.println("BookController addid:" + id);
	}
}
