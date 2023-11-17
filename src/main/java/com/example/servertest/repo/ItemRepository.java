package com.example.servertest.repo;

import com.example.servertest.dto.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByIdIn(List<Long> ID);
}
