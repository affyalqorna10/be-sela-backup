package com.data.sewalaptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.sewalaptop.model.Stock;

@Repository
public interface Stockrepository extends JpaRepository<Stock, Long> {
    
}
