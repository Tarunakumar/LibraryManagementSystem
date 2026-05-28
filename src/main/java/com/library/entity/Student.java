package com.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@NotBlank(message = "Name cannot be empty!")
private String name;
@NotBlank(message = "usn cannot empty!")
private String usn;
@Email(message = " Invalid email Format")
@NotBlank(message = "Email cannot be Empty!")
private String email;
@Pattern(
        regexp = "^[6-9][0-9]{9}$",
        message = "Mobile number must contain 10 digits"
    )
    private String mobile;
private Integer age;

}
