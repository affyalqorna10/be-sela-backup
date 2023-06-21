package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.MstKaryawan;
import com.data.sewalaptop.model.master.MstUser;
import com.data.sewalaptop.model.transaction.MstNotaDinas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.util.List;

@Repository
public interface KaryawanRepository extends PagingAndSortingRepository<MstKaryawan, Long> {

    @Query(value = "select ms.* from mst_karyawan ms where ms.karyawan_id= :karyawanId order by ms.karyawan_id asc limit 1",nativeQuery = true)
    MstKaryawan findByKaryawanId(@Param("karyawanId") Long karyawanId);

    @Query(value = "select ms.* from mst_karyawan ms where ms.divisi_id= :divisiId order by ms.karyawan_id asc ",nativeQuery = true)
    List<MstKaryawan> findAllByDivisiId(@Param("divisiId") Long divisiId);

    @Query(value = "select ms.* from mst_karyawan ms order by ms.karyawan_id asc ",nativeQuery = true)
    List<MstKaryawan> findAll();

    @Query(value = "select mu.* from mst_karyawan mu where lower(mu.email_karyawan)= lower(:emailKaryawan)",nativeQuery = true)
    MstKaryawan findByEmail(@Param("emailKaryawan") String emailKaryawan);

    @Query(value = "select mb.* from mst_karyawan mb where lower(mb.NIK_karyawan) = lower(:nikKaryawan) order by mb.karyawan_id asc limit 1",nativeQuery = true)
    MstKaryawan findByNikKaryawan(@Param("nikKaryawan") String nikKaryawan);
}
