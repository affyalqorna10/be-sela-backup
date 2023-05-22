package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<MstBrands, Long> {
    @Query(value = "select mb.* from mst_brands mb where mb.brand_id = :brandId",nativeQuery = true)
    MstBrands findByBrandId(@Param("brandId") Long brandId);

    @Query(value = "select mb.* from mst_brands mb where mb.brand_id = :brandId",nativeQuery = true)
    List<MstBrands> findAllByBrandId(@Param("brandId") Long brandId);

    @Query(value = "select mb.* from mst_brands mb where lower(mb.brand_name) = lower(:brandName) order by mb.brand_id asc limit 1",nativeQuery = true)
    MstBrands findByBrandName(@Param("brandName") String brandName);

    @Query(value = "select mb.* from mst_brands mb order by mb.brand_id asc ",nativeQuery = true)
    List<MstBrands> findAll();

}
