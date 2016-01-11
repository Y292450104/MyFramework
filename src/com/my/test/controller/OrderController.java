package com.my.test.controller;

import com.my.framework.annotation.Controller;
import com.my.framework.mvc.annotation.MappingUrl;

@Controller
@MappingUrl("order/")
public class OrderController {
	
	@MappingUrl
	public void add() {
		System.out.println("BookController add");
	}
	
	@MappingUrl
	public void del() {
		System.out.println("BookController del");
	}
}
