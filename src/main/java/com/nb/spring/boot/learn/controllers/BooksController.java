package com.nb.spring.boot.learn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nb.spring.boot.learn.entities.Book;
import com.nb.spring.boot.learn.services.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	private BooksService booksService;
	
	@GetMapping("/{id}")
	public Book getBook(@PathVariable Long id) {
		return booksService.getBook(id);
	}
}
