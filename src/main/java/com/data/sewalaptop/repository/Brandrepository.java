package com.data.sewalaptop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.data.sewalaptop.model.Brand;
import com.data.sewalaptop.model.Stock;

@Repository
public interface Brandrepository extends JpaRepository<Brand, Long> {
    @Query(value = "select mb.* from brands mb where mb.id = :brandId",nativeQuery = true)
    Brand findByBrandId(@Param("brandId") Long brandId);

    @Query(value = "select mb.* from stocks mb where mb.id = :stockId",nativeQuery = true)
    Stock findByBrandIdAll(@Param("stockId") Long stockId);

    @Query(value = "select code, status -> from stocks mb where mb.id= :stockId",nativeQuery = true)
    List<Stock> findByBrandIdList(@Param("stockId") Long stockId);
}

