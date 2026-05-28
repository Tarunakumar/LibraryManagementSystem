package com.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    @NotBlank(message = "Title Cannot be Empty")
    private String title;
    
    @NotBlank(message = "Author Cannot be Empty")
    private String author;
    
    @NotBlank(message = "Catogory  Cannot be Empty")
    private String category;
    
    @Min(value=1 ,message = "value mush be greater than 0")
    private double price;
    
    @Min(value=1 , message = "quantity cannot be Negative")
    private int quantity;


}
