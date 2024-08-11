package com.example.exercisejpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username should not be empty !")
    @Size(min = 5,message = "have to be more than 6 length long")
    @Pattern(regexp = ".*[A-Z0-9 ._,-].*")
    @Column(columnDefinition = "varchar(30) not null")
    private String username;

    @NotEmpty(message = "Email should not be empty !")
    @Email(message = "Email should contain '@' !")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @NotEmpty(message = "role should not be empty !")
    @Pattern(regexp = "^(Admin|Customer)$")
    @Column(columnDefinition = "varchar(9) not null")
    private String role;

    @NotNull(message = "Balance should not be empty !")
    @Positive(message = "balance should be positive number !")
    @Column(columnDefinition = "int not null")
    private int balance;

    //ArrayList<Product> cart;
}
