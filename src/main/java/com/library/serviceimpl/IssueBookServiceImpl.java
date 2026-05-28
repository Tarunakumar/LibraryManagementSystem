package com.library.serviceimpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.library.dto.IssueBookRequest;
import com.library.entity.Book;
import com.library.entity.IssueBook;
import com.library.entity.Student;
import com.library.repository.BookRepository;
import com.library.repository.IssueBookRepository;
import com.library.repository.StudentRepository;
import com.library.service.IssueBookService;

@Service
public class IssueBookServiceImpl
        implements IssueBookService {

    private IssueBookRepository
            issueBookRepository;

    private StudentRepository
            studentRepository;

    private BookRepository
            bookRepository;


    public IssueBookServiceImpl(

            IssueBookRepository
            issueBookRepository,

            StudentRepository
            studentRepository,

            BookRepository
            bookRepository) {

        this.issueBookRepository=
                issueBookRepository;

        this.studentRepository=
                studentRepository;

        this.bookRepository=
                bookRepository;
    }



    @Override
    public IssueBook issueBook(
            IssueBookRequest request) {

        Student student=

                studentRepository

                .findById(
                        request
                        .getStudentId())

                .orElseThrow(()->
                        new RuntimeException(
                        "Student not found"));



        Book book=

                bookRepository

                .findById(
                        request
                        .getBookId())

                .orElseThrow(()->
                        new RuntimeException(
                        "Book not found"));



        if(book.getQuantity()<=0){

            throw new RuntimeException(
                    "Book Out Of Stock");
        }



        book.setQuantity(

                book.getQuantity()-1);

        bookRepository.save(
                book);



        IssueBook issueBook=
                new IssueBook();


        issueBook.setStudent(
                student);

        issueBook.setBook(
                book);

        issueBook.setIssueDate(
                LocalDate.now());

        issueBook.setReturnDate(

                LocalDate.now()
                .plusDays(15));

        issueBook.setReturned(
                false);


        return issueBookRepository
                .save(issueBook);

    }



    @Override
    public String returnBook(int issueId) {
    	
    	System.out.println("RETURN API HIT");

        IssueBook issueBook =

                issueBookRepository
                .findById(issueId)

                .orElseThrow(() ->
                        new RuntimeException(
                        "Issue record not found"));


        Book book =
                issueBook.getBook();


        long lateDays =

                ChronoUnit.DAYS.between(

                        issueBook
                        .getReturnDate(),

                        LocalDate.now());


        double fine = 0;


        if(lateDays > 0){

            fine = lateDays * 10;
        }


        issueBook.setFine(
                fine);


        book.setQuantity(

                book.getQuantity()+1);

        bookRepository.save(
                book);


        // IMPORTANT PART

        issueBook.setReturned(
                true);

        System.out.println(
                issueBook.isReturned());


        issueBookRepository
                .save(issueBook);


        return
        "Book Returned. Fine ₹"
                + fine;
    }

}