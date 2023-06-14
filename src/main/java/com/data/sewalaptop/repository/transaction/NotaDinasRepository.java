package com.data.sewalaptop.repository.transaction;

import com.data.sewalaptop.model.transaction.MstNotaDinas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotaDinasRepository extends PagingAndSortingRepository<MstNotaDinas, Long> {
    @Query(value = "select mu.* from mst_karyawan mu where lower(mu.NIK)= lower(:NIK)",nativeQuery = true)
    MstNotaDinas findByNIK(@Param("NIK") String NIK);

    @Query(value = "select ms.* from mst_karyawan ms where ms.id= :karyawanId",nativeQuery = true)
    MstNotaDinas findByKaryawanId(@Param("karyawanId") Long karyawanId);

    @Query(value = "select mb.* from nota_dinas mb where lower(mb.nota_dinas) = lower(:notaDinas) order by mb.nodin_id asc limit 1",nativeQuery = true)
    MstNotaDinas findByNotaDinas(@Param("notaDinas") String notaDinas);

    @Query(value = "select mb.* from nota_dinas mb where mb.nodin_id = :nodinId",nativeQuery = true)
    MstNotaDinas findByNodinId(@Param("nodinId") Long nodinId);

    @Query(value = "select mb.* from nota_dinas mb order by mb.nodin_id asc ",nativeQuery = true)
    List<MstNotaDinas> findAll();
}
