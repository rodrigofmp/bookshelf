package com.rodrigofmp.bookshelf.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.rodrigofmp.bookshelf.entity.Book;

@RepositoryRestResource(path = "books", collectionResourceRel = "books")
public interface BooksRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>, QuerydslPredicateExecutor<Book> {

	Page<Book> findByIdIn(@Param(value = "id") List<Long> eventid, Pageable pageable);

	Page<Book> findByNameIn(@Param("name") Collection<String> names, Pageable pageable);

	@Query(name="Book.findByName", nativeQuery = true)
	List<Book> findByName(@Param("name") String name);

	Page<Book> findAll(Pageable pageable);

	@Query(name="Book.findById", nativeQuery = true)
	@RestResource(exported = false)
	Optional<Book> findById(@Param("id") long id);
}
