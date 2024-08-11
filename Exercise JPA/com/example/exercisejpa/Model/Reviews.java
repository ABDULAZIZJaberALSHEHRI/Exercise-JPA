package com.example.exercisejpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reviews {
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

    @NotEmpty(message = "review should not be empty!")
    @Column(columnDefinition = "varchar(50) not null")
    private String review;
}
