package com.data.sewalaptop.repository.transaction;

import com.data.sewalaptop.model.transaction.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TrxDetailStockRepository extends PagingAndSortingRepository<TrxDetailStock, Long>{

    @Query(value = "select tds.* from trx_detail_stock tds where tds.brand_id = :brandId",nativeQuery = true)
    TrxDetailStock findByBrandId(@Param("brandId") Long brandId);

    @Query(value = "select tds.* from trx_detail_stock tds order by tds.brand_id asc ",nativeQuery = true)
    List<TrxDetailStock> findAllNoPageble();

}
