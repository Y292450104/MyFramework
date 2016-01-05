package com.my.test.service;

import com.my.framework.annotation.Service;

@Service
public class UserService {

	public String add() {
		System.out.println("UserService.add()");
		return "";
	}
}
