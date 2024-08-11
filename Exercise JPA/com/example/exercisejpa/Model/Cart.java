package com.example.exercisejpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "product id should not be null!")
    @Positive(message = "product id should be positive number!")
    @Column(columnDefinition = "int not null")
    private int productID;

    @NotNull(message = "user id should not be null!")
    @Positive(message = "user id should be positive number!")
    @Column(columnDefinition = "int not null")
    private int userID;

    @NotEmpty(message = "Product name should not be empty !")
    @Size(min = 3, message = "Product name should be more than 3 characters")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

    @NotNull(message = "Product price should not be empty !")
    @Positive(message = "Product price should be positive number !")
    @Column(columnDefinition = "int not null")
    private int price;

    @NotNull(message = "Product categoryID should not be empty !")
    @Column(columnDefinition = "int not null")
    private Integer categoryID;

    //ArrayList<String> reviews;
    @Column(columnDefinition = "varchar(20)")
    private String offer;
}
