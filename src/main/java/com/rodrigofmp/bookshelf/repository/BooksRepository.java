package com.rodrigofmp.bookshelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rodrigofmp.bookshelf.entity.Book;

public interface BooksRepository extends JpaRepository<Book, Long> {

}
