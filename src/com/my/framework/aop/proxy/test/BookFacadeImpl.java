package com.my.framework.aop.proxy.test;

import com.my.framework.annotation.Service;

@Service
public class BookFacadeImpl implements BookFacade {
	private int id = 0;
	
	public void id(int id) {
		this.id = id;
	}
	
	@Override
	public void addBook() {
		// TODO Auto-generated method stub
		System.out.println("����ͼ�鷽�������� id:" + id);
	}

}
