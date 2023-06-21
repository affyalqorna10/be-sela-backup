package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.MstPengajuan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PengajuanRepository extends PagingAndSortingRepository<MstPengajuan, Long> {

    @Query(value = "select ms.* from mst_pengajuan ms where ms.pengajuan_id = :pengajuanId",nativeQuery = true)
    MstPengajuan findByPengajuanId(@Param("pengajuanId") Long pengajuanId);

    @Query(value = "SELECT * FROM mst_pengajuan AS pengajuan LEFT JOIN mst_spesifikasi AS spek ON pengajuan.spek_id = spek.spek_id LEFT JOIN mst_karyawan AS karyawan ON pengajuan.karyawan_id = karyawan.karyawan_id",nativeQuery = true)
    List<MstPengajuan> findAll();

    @Query(value = "SELECT * FROM mst_pengajuan AS pengajuan LEFT JOIN mst_spesifikasi AS spek ON pengajuan.spek_id = spek.spek_id LEFT JOIN mst_karyawan AS karyawan ON pengajuan.karyawan_id = karyawan.karyawan_id order by pengajuan_id asc limit 1",nativeQuery = true)
    List<MstPengajuan> findByAllPengajuanId(@Param("pengajuanId") Long pengajuanId);

    @Query(value = "select mb.* from pengajuan mb where lower(mb.no_memo) = lower(:noMemo) order by mb.pengajuan_id asc limit 1",nativeQuery = true)
    MstPengajuan findByNoMemo(@Param("noMemo") String noMemo);

    @Query(value = "select ms.* from pengajuan ms where ms.nodin_id= :nodinId order by ms.pengajuan_id asc ",nativeQuery = true)
    List<MstPengajuan> findByNodinId(@Param("nodinId") Long nodinId);
}
