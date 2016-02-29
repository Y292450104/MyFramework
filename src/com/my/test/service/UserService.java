package com.my.test.service;

import java.util.Date;

import com.my.framework.annotation.Service;

@Service
public class UserService {

	public String add() {
		System.out.println(new Date() + "UserService.add()");
		return "";
	}
}
