package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SpesificationRepository extends PagingAndSortingRepository<MstSpesifikasi, Long> {
    @Query(value = "select ms.* from mst_spesifikasi ms where ms.brand_id= :brandId order by ms.spek_id asc limit 1",nativeQuery = true)
    MstSpesifikasi findByBrandId(@Param("brandId") Long brandId);

    @Query(value = "select ms.* from mst_spesifikasi ms where ms.spek_id= :spekId order by ms.spek_id asc limit 1",nativeQuery = true)
    MstSpesifikasi findBySpekId(@Param("spekId") Long spekId);

    @Query(value = "select ms.* from mst_spesifikasi ms where ms.brand_id= :brandId",nativeQuery = true)
    List<MstSpesifikasi> findAllByBrandId(@Param("brandId") Long brandId);

    @Query(value = "select ms.* from mst_spesifikasi ms order by ms.spek_id asc ",nativeQuery = true)
    List<MstSpesifikasi> findAll();
}
