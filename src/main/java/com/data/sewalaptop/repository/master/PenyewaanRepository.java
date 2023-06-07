package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.MstPenyewaan;
import com.data.sewalaptop.model.master.Pengajuan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenyewaanRepository extends PagingAndSortingRepository<MstPenyewaan, Long> {

    @Query(value = "select ms.* from penyewaan ms where ms.penyewaan_id = :penyewaanId",nativeQuery = true)
    MstPenyewaan findByPenyewaanId(@Param("penyewaanId") Long penyewaanId);

    @Query(value = "select ms.* from penyewaan ms order by ms.penyewaan_id asc ",nativeQuery = true)
    List<MstPenyewaan> findAll();
}
