package com.library.service;

import com.library.dto.IssueBookRequest;
import com.library.entity.IssueBook;

public interface IssueBookService {

	IssueBook issueBook(IssueBookRequest request);
	
	String returnBook(int issueId);
		
	
}
