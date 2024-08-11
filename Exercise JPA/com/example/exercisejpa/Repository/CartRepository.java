package com.example.exercisejpa.Repository;

import com.example.exercisejpa.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
