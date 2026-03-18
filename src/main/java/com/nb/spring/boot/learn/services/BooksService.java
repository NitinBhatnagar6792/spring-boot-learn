package com.nb.spring.boot.learn.services;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nb.spring.boot.learn.entities.Book;
import com.nb.spring.boot.learn.exceptions.BadRequestException;
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

	public List<Book> getAllBooks() {
		return booksRepository.findAll();
	}

	public @Nullable Book createBook(Book newBook) {
		
		Book existingBook = booksRepository.findFirstByName(newBook.getName());
		if (existingBook != null) {
			throw new BadRequestException("Book already exists by provided name:"+newBook.getName());
		}
		return booksRepository.save(newBook);
	}

	public @Nullable Book updateBook(Book book) {
		Book existingBook = booksRepository.findFirstByName(book.getName());
		if (existingBook == null) {
			throw new BadRequestException("Book does not exists by provided name:"+book.getName());
		}
		existingBook.setAuthor(book.getAuthor());
		return booksRepository.save(existingBook);
	}

	public void deleteBook(Long id) {
		Optional<Book> bookOptional = booksRepository.findById(id);
		if (!bookOptional.isPresent()) {
			throw new NotFoundException("Book Not Found");
		}
		booksRepository.delete(bookOptional.get());
	}

}
