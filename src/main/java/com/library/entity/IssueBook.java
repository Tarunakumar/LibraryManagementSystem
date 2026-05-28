package com.library.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate issueDate;
	
	private LocalDate returnDate;
	
	private double fine;
	
	private boolean returned=false;
	
	@ManyToOne
	@JoinColumn(name ="student_id")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	
	

}
