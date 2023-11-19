package com.example.servertest.repo;

import com.example.servertest.dto.Inven;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvenRepository extends JpaRepository<Inven,Long> {
    List<Inven> findByUserEmail(String userEmail);
}
