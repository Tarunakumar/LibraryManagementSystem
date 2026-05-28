package com.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.dto.IssueBookRequest;
import com.library.entity.IssueBook;
import com.library.repository.IssueBookRepository;
import com.library.service.IssueBookService;

@RestController
@RequestMapping("/issue")
public class IssueBookController {

    private IssueBookService issueBookService;

    private IssueBookRepository issueBookRepository;


    public IssueBookController(

            IssueBookService issueBookService,

            IssueBookRepository issueBookRepository){

        this.issueBookService=
                issueBookService;

        this.issueBookRepository=
                issueBookRepository;
    }


    @GetMapping
    public List<IssueBook>
    getAllIssuedBooks(){

        return issueBookRepository
                .findAll();
    }


    @PostMapping
    public IssueBook issueBook(

            @RequestBody
            IssueBookRequest request){

        return issueBookService
                .issueBook(request);
    }


    @PutMapping("/return/{id}")
    public String returnBook(
            @PathVariable int id){

        return issueBookService
                .returnBook(id);
    }

}