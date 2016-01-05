package com.my.test.controller;

import com.my.framework.annotation.Controller;

@Controller
public class UserController {

	public String add() {
		System.out.println("UserController.add()");
		return "";
	}
}
