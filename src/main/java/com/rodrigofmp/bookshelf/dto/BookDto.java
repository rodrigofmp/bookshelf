package com.rodrigofmp.bookshelf.dto;

public class BookDto {
	
	private String name;
	private String author;
	
	BookDto() {}
	
	public BookDto(String name, String author) {
		super();
		this.name = name;
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
