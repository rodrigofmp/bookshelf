package com.rodrigofmp.bookshelf.exception;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotFoundException(long id) {
		super("Could not find book " + id);
	}

}
