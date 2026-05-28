package com.library.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.entity.Book;
import com.library.exception.BookNotFoundException;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    
    // Constructor Injection
    public BookServiceImpl(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    
    // Save Book
    @Override
    public Book saveBook(Book book) {

        return bookRepository.save(book);
    }

    
    // Get All Books
    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    
    // Get Book By Id
    @Override
    public Book getBookById(Integer id) {

        return bookRepository.findById(id).orElseThrow(() ->
                          new BookNotFoundException("Book not found with Id :" + id));
    }

    
    // Update Book
    @Override
    public Book updateBook(Integer id, Book book) {

        Book existingBook = bookRepository.findById(id).orElse(null);

        if(existingBook != null) {

            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setCategory(book.getCategory());
            existingBook.setPrice(book.getPrice());
            existingBook.setQuantity(book.getQuantity());

            return bookRepository.save(existingBook);
        }

        return null;
    }

    
    // Delete Book
    @Override
    public String deleteBook(Integer id) {

        bookRepository.deleteById(id);

        return "Book Deleted Successfully";
    }
    
    
    
    @Override
    public List<Book> getBooksByTitle(String title){

        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> getBooksByAuthor(String author){

        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> getBooksByCategory(String category){

        return bookRepository.findByCategory(category);
    }
    

@Override
public Page<Book> getBooksWithPagination(
        int page,
        int size,
        String sortBy){

    Pageable pageable =
            PageRequest.of(
                    page,
                    size,
                    Sort.by(sortBy));

    return bookRepository.findAll(pageable);
}
    
}