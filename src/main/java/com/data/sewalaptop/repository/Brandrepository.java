package com.data.sewalaptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.sewalaptop.model.Brand;

@Repository
public interface Brandrepository extends JpaRepository<Brand, Long> {
    
}
