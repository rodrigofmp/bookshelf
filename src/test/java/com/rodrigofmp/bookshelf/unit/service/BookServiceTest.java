package com.rodrigofmp.bookshelf.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rodrigofmp.bookshelf.dto.BookDto;
import com.rodrigofmp.bookshelf.entity.Book;
import com.rodrigofmp.bookshelf.exception.BookNotFoundException;
import com.rodrigofmp.bookshelf.mock.BookMock;
import com.rodrigofmp.bookshelf.repository.BooksRepository;
import com.rodrigofmp.bookshelf.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@Mock
	BooksRepository booksRepository;

	private BookService bookService;

	@BeforeEach
	public void setup() {
		bookService = new BookService(booksRepository);

		Mockito.lenient().when(booksRepository.findAll()).thenReturn(BookMock.createBooks());
	}

	@Test
	@DisplayName("Should get all books")
	public void shouldGetBooks() {
		List<Book> books = bookService.getBooks();

		assertEquals(books.size(), 2);
		assertNotNull(books);
	}

	@Test
	@DisplayName("Should get existing book")
	public void shouldGetBook() {
		Optional<Book> mockBook = Optional.of(BookMock.createBooks().get(0));
		Mockito.lenient().when(booksRepository.findById(1L)).thenReturn(mockBook);

		Book book = bookService.getBook(1);

		assertEquals(book.getId(), mockBook.get().getId());
		assertEquals(book.getName(), mockBook.get().getName());
		assertEquals(book.getAuthor(), mockBook.get().getAuthor());
	}

	@Test
	@DisplayName("Should throw exception when trying to get a missing book")
	public void shouldThrowExceptionForGetMissingBook() {
		Mockito.lenient().when(booksRepository.findById(1L)).thenReturn(Optional.empty());

		BookNotFoundException thrown = assertThrows(BookNotFoundException.class, () -> {
			bookService.getBook(1);
		});

		assertTrue(thrown.getMessage().contains("Could not find book " + 1L));
	}

	@Test
	@DisplayName("Should create book")
	public void shouldCreateBook() {
		String bookName = "The Mists of Avalon";
		String bookAuthor = "Marion Zimmer Bradley";

		BookDto bookDto = new BookDto(bookName, bookAuthor);
		bookService.createBook(bookDto);

		verify(booksRepository, only())
				.save(Mockito.argThat((Book book) -> book.getName() == bookName && book.getAuthor() == bookAuthor));
	}

	@Test
	@DisplayName("Should update book")
	public void shouldUpdateBook() {
		String bookName = "The Mists of Avalon";
		String bookAuthor = "Marion Zimmer Bradley";

		BookDto bookDto = new BookDto(bookName, bookAuthor);

		Optional<Book> mockBook = Optional.of(BookMock.createBooks().get(0));
		Mockito.lenient().when(booksRepository.findById(1L)).thenReturn(mockBook);

		Book savedBook = new Book(bookName, bookAuthor);
		Mockito.lenient().when(booksRepository.save(Mockito.any())).thenReturn(savedBook);

		Book book = bookService.updateBook(bookDto, 1L);

		verify(booksRepository).save(
				Mockito.argThat((Book argBook) -> argBook.getName() == bookName && argBook.getAuthor() == bookAuthor));

		assertEquals(book.getName(), bookDto.getName());
		assertEquals(book.getAuthor(), bookDto.getAuthor());
	}

	@Test
	@DisplayName("Should throw exception when trying to update a missing book")
	public void shouldThrowExceptionForUpdateMissingBook() {
		Mockito.lenient().when(booksRepository.findById(1L)).thenReturn(Optional.empty());

		BookNotFoundException thrown = assertThrows(BookNotFoundException.class, () -> {
			BookDto bookDto = new BookDto("", "");
			bookService.updateBook(bookDto, 1L);
		});

		assertTrue(thrown.getMessage().contains("Could not find book " + 1L));
	}

	@Test
	@DisplayName("Should delete book")
	public void shouldDeleteBook() {
		Optional<Book> mockBook = Optional.of(BookMock.createBooks().get(0));
		Mockito.lenient().when(booksRepository.findById(1L)).thenReturn(mockBook);

		bookService.deleteBook(1L);

		verify(booksRepository).deleteById(Mockito.anyLong());
	}

	@Test
	@DisplayName("Should exception when trying to delete a missing book")
	public void shouldThrowExceptionForDeleteMissingBook() {
		Mockito.lenient().when(booksRepository.findById(1L)).thenReturn(Optional.empty());

		BookNotFoundException thrown = assertThrows(BookNotFoundException.class, () -> {
			bookService.deleteBook(1L);
		});

		assertTrue(thrown.getMessage().contains("Could not find book " + 1L));
	}
}
