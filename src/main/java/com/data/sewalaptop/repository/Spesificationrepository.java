package com.data.sewalaptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.data.sewalaptop.model.Spesification;

@Repository
public interface Spesificationrepository extends JpaRepository<Spesification, Long> {
    @Query(value = "select ms.* from spesifications ms where ms.brand_id= :brandId",nativeQuery = true)
    Spesification findByBrandId(@Param("brandId") Long brandId);
}
