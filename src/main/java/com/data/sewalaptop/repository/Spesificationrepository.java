package com.data.sewalaptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.sewalaptop.model.Spesification;

@Repository
public interface Spesificationrepository extends JpaRepository<Spesification, Long> {
    
}
