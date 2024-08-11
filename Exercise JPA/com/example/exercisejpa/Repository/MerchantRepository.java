package com.example.exercisejpa.Repository;

import com.example.exercisejpa.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
}
