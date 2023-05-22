package com.data.sewalaptop.repository.transaction;

import com.data.sewalaptop.model.transaction.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TrxCustomerStockRepository extends PagingAndSortingRepository<TrxCustomerStock, Long> {
    @Query(value = "select tcs.* from trx_customer_stock tcs where tcs.id = :id",nativeQuery = true)
    TrxCustomerStock findByCsId(@Param("id") Long id);

    @Query(value = "select tcs.* from trx_customer_stock tcs where tcs.brand_id = :brandId",nativeQuery = true)
    List<TrxCustomerStock> findByBrandId(@Param("brandId") Long brandId);

    @Query(value = "select tcs.* from trx_customer_stock tcs order by tcs.id asc ",nativeQuery = true)
    List<TrxCustomerStock> findAllNoPageble();
}
