package com.library.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.library.entity.Book;
import com.library.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    
    // Constructor Injection
    public BookController(BookService bookService) {

        this.bookService = bookService;
    }

    
    // Save Book
    @PostMapping
    public Book saveBook(@Valid  @RequestBody Book book) {

        return bookService.saveBook(book);
    }

    
    // Get All Books
    @GetMapping
    public List<Book> getAllBooks() {

        return bookService.getAllBooks();
    }

    
    // Get Book By Id
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {

        return bookService.getBookById(id);
    }

    
    // Update Book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id,
                           @RequestBody Book book) {

        return bookService.updateBook(id, book);
    }

    
    // Delete Book
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {

        return bookService.deleteBook(id);
    }
    
    
    //fetch book by title ,author ,category
    
    @GetMapping("/title/{title}")
    public List<Book> getBookByTitle(
            @PathVariable String title){

        return bookService
                .getBooksByTitle(title);
    }


    @GetMapping("/author/{author}")
    public List<Book> getBookByAuthor(
            @PathVariable String author){

        return bookService
                .getBooksByAuthor(author);
    }


    @GetMapping("/category/{category}")
    public List<Book> getBookByCategory(
            @PathVariable String category){

        return bookService
                .getBooksByCategory(category);
    }
    
    
    
    
    @GetMapping("/pagination")
    public Page<Book> getBooksWithPagination(

    @RequestParam(defaultValue="0")
    int page,

    @RequestParam(defaultValue="5")
    int size,

    @RequestParam(defaultValue="id")
    String sortBy){

        return bookService
                .getBooksWithPagination(
                        page,
                        size,
                        sortBy);
    }
}