package com.data.sewalaptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.data.sewalaptop.model.Brand;

@Repository
public interface Brandrepository extends JpaRepository<Brand, Long> {
    @Query(value = "select mb.* from brands mb where mb.id = :brandId",nativeQuery = true)
    Brand findByBrandId(@Param("brandId") Long brandId);
}
