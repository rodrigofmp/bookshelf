package com.rodrigofmp.bookshelf.integration;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.rodrigofmp.bookshelf.BookshelfApplication;
import com.rodrigofmp.bookshelf.dto.BookDto;
import com.rodrigofmp.bookshelf.entity.Book;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { BookshelfApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class BookControllerTest {

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost:8080";
		RestAssured.port = 8080;
	}

	@Test
	public void Should_Read_Books() {
		get("/api/bookshelf/books").then().statusCode(200);
	}

	@Test
	public void Should_Read_Book() {
		get("/api/bookshelf/books/1").then().statusCode(200).assertThat().body("name", equalTo("The Lord of the Rings"),
				"author", equalTo("J. R. R. Tolkien"));
	}

	@Test
	public void Should_Create_Book() {
		String bookName = "Alice's Adventures in Wonderland";
		String bookAuthor = "Lewis Carroll";
		BookDto bookDto = new BookDto(bookName, bookAuthor);

		Response response = given().header("Content-type", "application/json").and().body(bookDto).when()
				.post("/api/bookshelf/books").then().extract().response();

		Book book = response.getBody().as(Book.class);

		Assertions.assertEquals(200, response.statusCode());
		Assertions.assertEquals(book.getName(), bookName);
		Assertions.assertEquals(book.getAuthor(), bookAuthor);
	}

	@Test
	public void Should_Update_Book() {
		String bookName = "Alice's Adventures in Wonderland";
		String bookAuthor = "Lewis Carroll";
		BookDto bookDto = new BookDto(bookName, bookAuthor);

		Response response = given().header("Content-type", "application/json").and().body(bookDto).when()
				.post("/api/bookshelf/books/2").then().extract().response();

		Book book = response.getBody().as(Book.class);

		Assertions.assertEquals(200, response.statusCode());
		Assertions.assertEquals(book.getId(), 2L);
		Assertions.assertEquals(book.getName(), bookName);
		Assertions.assertEquals(book.getAuthor(), bookAuthor);
	}
	
	@Test
	public void Should_Delete_Book() {
		delete("/api/bookshelf/books/3").then().statusCode(200);
	}	

}
