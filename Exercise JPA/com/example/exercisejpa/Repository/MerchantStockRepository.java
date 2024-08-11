package com.example.exercisejpa.Repository;

import com.example.exercisejpa.Model.MerchantStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantStockRepository extends JpaRepository<MerchantStock, Integer> {
    MerchantStock findByProductID(int productId);
}
