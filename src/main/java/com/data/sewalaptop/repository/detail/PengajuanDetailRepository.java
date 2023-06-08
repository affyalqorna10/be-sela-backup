package com.data.sewalaptop.repository.detail;

import com.data.sewalaptop.model.detail.PengajuanDetail;
import com.data.sewalaptop.model.master.Pengajuan;
import com.data.sewalaptop.model.transaction.TrxDetailStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PengajuanDetailRepository extends PagingAndSortingRepository<PengajuanDetail, Long> {

    @Query(value = "select tds.* from pengajuan_detail tds where tds.pengajuandetail_id = :pengajuandetailId",nativeQuery = true)
    PengajuanDetail findByPengajuanDetailId(@Param("pengajuandetailId") Long pengajuandetailId);

    @Query(value = "select ms.* from pengajuan_detail ms order by ms.pengajuandetail_id asc ",nativeQuery = true)
    List<PengajuanDetail> findAll();
}
