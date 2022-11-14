package com.rodrigofmp.bookshelf.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigofmp.bookshelf.dto.BookDto;
import com.rodrigofmp.bookshelf.entity.Book;
import com.rodrigofmp.bookshelf.service.BookService;

@RestController
@RequestMapping("/bookshelf")
public class BookController {

	private BookService bookService = null;

	BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/books")
	List<Book> all() {
		return bookService.getBooks();
	}
	
	@GetMapping("/books/{id}")
	Book get(@PathVariable Long id) {
		return bookService.getBook(id);
	}	

	@PostMapping("/books")
	Book createBook(@RequestBody BookDto bookDto) {
		return bookService.createBook(bookDto);
	}
	
	@PostMapping("/books/{id}")
	Book updateBook(@RequestBody BookDto bookDto, @PathVariable Long id) {
		return bookService.updateBook(bookDto, id);
	}
	
	@DeleteMapping("/books/{id}")
	void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}	



}
