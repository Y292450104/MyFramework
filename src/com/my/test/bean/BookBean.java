package com.my.test.bean;

import com.my.framework.mvc.annotation.RequestParam;

public class BookBean {
	@RequestParam("book_id")
	private Long id;
	@RequestParam("book_name")
	private String name;
	@RequestParam("book_price")
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "BookBean id:" + id +" name:" + name + " price:" + price;
	}

}
