package com.example.servertest.repo;

import com.example.servertest.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
