package com.library.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.library.entity.Book;

public interface BookService {

    // Save Book
    Book saveBook(Book book);

    // Get All Books
    List<Book> getAllBooks();

    // Get Book By Id
    Book getBookById(Integer id);

    // Update Book
    Book updateBook(Integer id, Book book);

    // Delete Book
    String deleteBook(Integer id);
    
    List<Book> getBooksByTitle(String title);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByCategory(String category);
    
    Page<Book> getBooksWithPagination(
            int page,
            int size,
            String sortBy);
}