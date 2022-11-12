package com.rodrigofmp.bookshelf.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity(name="Books")
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	@NotNull(message="{NotNull.Book.name}")
	private String name;
	
	@Column
	@NotNull(message="{NotNull.Book.author}")
	private String author;
}
