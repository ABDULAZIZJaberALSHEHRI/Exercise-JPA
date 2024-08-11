package com.example.exercisejpa.Repository;

import com.example.exercisejpa.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
