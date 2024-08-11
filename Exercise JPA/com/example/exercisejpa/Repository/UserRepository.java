package com.example.exercisejpa.Repository;

import com.example.exercisejpa.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
