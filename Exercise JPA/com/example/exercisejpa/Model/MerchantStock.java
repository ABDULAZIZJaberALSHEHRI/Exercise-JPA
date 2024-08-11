package com.example.exercisejpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "ProductID should not be empty !")
    @Column(columnDefinition = "int not null")
    private int productID;

    @NotNull(message = "MerchantID should not be empty !")
    @Column(columnDefinition = "int not null")
    private int merchantID;

    @NotNull(message = "Stock should not be empty !")
    @Min(value = 10, message = "Stock have to be more than 10 at start")
    @Column(columnDefinition = "int not null")
    private int stock;
}
