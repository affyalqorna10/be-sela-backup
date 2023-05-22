package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StockRepository extends PagingAndSortingRepository<MstStock, Long> {

    @Query(value = "select ms.* from mst_stock ms order by ms.stock_id asc ",nativeQuery = true)
    List<MstStock> findAll();

    @Query(value = "select ms.* from mst_stock ms where ms.stock_id= :stockId order by stock_id asc limit 1",nativeQuery = true)
    MstStock findByStockId(@Param("stockId") Long stockId);

    @Query(value = "select ms.* from mst_stock ms where ms.brand_id= :brandId order by stock_id asc limit 1",nativeQuery = true)
    MstStock findByBrandId(@Param("brandId") Long brandId);

    @Query(value = "select ms.* from mst_stock ms where ms.brand_id= :brandId order by ms.stock_id asc ",nativeQuery = true)
    List<MstStock> findAllByBrandId(@Param("brandId") Long brandId);
}
