package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.MstDivisi;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisiRespository extends PagingAndSortingRepository<MstDivisi, Long> {
    @Query(value = "select mb.* from mst_divisis mb where mb.divisi_id = :divisiId",nativeQuery = true)
    MstDivisi findByDivisiId(@Param("divisiId") Long divisiId);

    @Query(value = "select mb.* from mst_divisis mb where lower(mb.nama_divisi) = lower(:namaDivisi) order by mb.divisi_id asc limit 1",nativeQuery = true)
    MstDivisi findByDivisiName(@Param("namaDivisi") String namaDivisi);

    @Query(value = "select mb.* from mst_divisis mb order by mb.divisi_id asc ",nativeQuery = true)
    List<MstDivisi> findAll();
}
