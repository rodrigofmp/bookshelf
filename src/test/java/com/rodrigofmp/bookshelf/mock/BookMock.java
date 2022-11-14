package com.rodrigofmp.bookshelf.mock;

import java.util.ArrayList;
import java.util.List;

import com.rodrigofmp.bookshelf.entity.Book;

public class BookMock {

	public static List<Book> createBooks() {

		List<Book> bookList = new ArrayList<>();
		Book book1 = new Book();
		book1.setId(1);
		book1.setName("Icon");
		book1.setAuthor("Frederick Forsyth");

		Book book2 = new Book();
		book2.setId(2);
		book2.setName("Wuthering Heights");
		book2.setAuthor("Emily Brontë");

		bookList.add(book1);
		bookList.add(book2);

		return bookList;
	}

}
