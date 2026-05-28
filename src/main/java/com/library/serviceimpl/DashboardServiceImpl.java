package com.library.serviceimpl;

import org.springframework.stereotype.Service;

import com.library.dto.DashboardResponse;
import com.library.repository.BookRepository;
import com.library.repository.IssueBookRepository;
import com.library.repository.StudentRepository;
import com.library.service.DashboardService;

@Service
public class DashboardServiceImpl
        implements DashboardService {

    private BookRepository bookRepository;
    private StudentRepository studentRepository;
    private IssueBookRepository issueBookRepository;


    public DashboardServiceImpl(
            BookRepository bookRepository,
            StudentRepository studentRepository,
            IssueBookRepository issueBookRepository){

        this.bookRepository=bookRepository;
        this.studentRepository=studentRepository;
        this.issueBookRepository=issueBookRepository;
    }


    @Override
    public DashboardResponse getDashboard(){

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalBooks(
                bookRepository.count());

        response.setTotalStudents(
                studentRepository.count());

        response.setTotalIssuedBooks(
                issueBookRepository.count());

        response.setAvailableBooks(
                bookRepository
                        .countByQuantityGreaterThan(0));

        response.setOutOfStockBooks(
                bookRepository
                        .countByQuantityEquals(0));

        return response;
    }
}