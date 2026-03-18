package com.nb.spring.boot.learn.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nb.spring.boot.learn.entities.Book;
import com.nb.spring.boot.learn.exceptions.NotFoundException;
import com.nb.spring.boot.learn.repositories.BooksRepository;

@Service
public class BooksService {

	@Autowired
	private BooksRepository booksRepository;
	
	public Book getBook(Long id) {
		Optional<Book> bookOptional = booksRepository.findById(id);
		if (!bookOptional.isPresent()) {
			throw new NotFoundException("Book Not Found");
		}
		return bookOptional.get();
	}

}
