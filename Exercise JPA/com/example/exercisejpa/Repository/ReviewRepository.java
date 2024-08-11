package com.example.exercisejpa.Repository;

import com.example.exercisejpa.Model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews,Integer> {
}
