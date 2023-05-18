package com.data.sewalaptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.data.sewalaptop.model.Stock;

@Repository
public interface Stockrepository extends JpaRepository<Stock, Long> {
    @Query(value = "select ms.* from stocks ms where ms.brand_id= :brandId",nativeQuery = true)
    Stock findByBrandId(@Param("brandId") Long brandId);
}
