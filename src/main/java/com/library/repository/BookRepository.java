package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	List<Book> findByTitle(String title);
	List<Book> findByAuthor(String author);
	List<Book> findByCategory(String category);
	
	long countByQuantityGreaterThan(int quantity);

	long countByQuantityEquals(int quantity);

}
