package com.rodrigofmp.bookshelf.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.rodrigofmp.bookshelf.dto.BookDto;
import com.rodrigofmp.bookshelf.entity.Book;
import com.rodrigofmp.bookshelf.exception.BookNotFoundException;
import com.rodrigofmp.bookshelf.repository.BooksRepository;

@Service
public class BookService {

	private BooksRepository booksRepository;

	public BookService(BooksRepository booksRepository) {
		this.booksRepository = booksRepository;
	}

	public List<Book> getBooks() {
		return booksRepository.findAll();
	}

	public Book getBook(long id) {
		Optional<Book> book = booksRepository.findById(id);
		return book.get();
	}

	public Book createBook(BookDto bookDto) {
		ModelMapper modelMapper = new ModelMapper();
		Book book = modelMapper.map(bookDto, Book.class);
		return booksRepository.save(book);
	}

	public Book saveBook(BookDto bookDto, long id) {
		return booksRepository.findById(id).map(book -> {
			book.setName(bookDto.getName());
			book.setAuthor(bookDto.getAuthor());
			return booksRepository.save(book);
		}).orElseThrow(() -> new BookNotFoundException(id));
	}

	public void deleteBook(long id) {
		booksRepository.findById(id)
	      .orElseThrow(() -> new BookNotFoundException(id));
		
		booksRepository.deleteById(id);		
	}
}
