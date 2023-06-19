package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.MstKaryawan;
import com.data.sewalaptop.model.master.Pengajuan;
import com.data.sewalaptop.model.transaction.TrxDetailStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PengajuanRepository extends PagingAndSortingRepository<Pengajuan, Long> {

    @Query(value = "select ms.* from pengajuan ms where ms.pengajuan_id = :pengajuanId",nativeQuery = true)
    Pengajuan findByPengajuanId(@Param("pengajuanId") Long pengajuanId);

    @Query(value = "select ms.* from pengajuan ms order by ms.pengajuan_id asc",nativeQuery = true)
    List<Pengajuan> findAll();

    @Query(value = "select mb.* from pengajuan mb where lower(mb.no_memo) = lower(:noMemo) order by mb.pengajuan_id asc limit 1",nativeQuery = true)
    Pengajuan findByNoMemo(@Param("noMemo") String noMemo);

    @Query(value = "select ms.* from pengajuan ms where ms.nodin_id= :nodinId order by ms.pengajuan_id asc ",nativeQuery = true)
    List<Pengajuan> findByNodinId(@Param("nodinId") Long nodinId);
}
