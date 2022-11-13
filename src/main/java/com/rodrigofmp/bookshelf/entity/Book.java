package com.rodrigofmp.bookshelf.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Entity(name="Books")
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;
	
	@Column
	@NotNull(message="{NotNull.Book.name}")
	@JsonSerialize
	private String name;
	
	@Column
	@NotNull(message="{NotNull.Book.author}")
	@JsonSerialize
	private String author;
}
