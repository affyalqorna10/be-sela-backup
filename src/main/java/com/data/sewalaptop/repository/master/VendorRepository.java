package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.MstVendor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends PagingAndSortingRepository<MstVendor, Long> {
    @Query(value = "select mb.* from mst_vendor mb where mb.vendor_id = :vendorId",nativeQuery = true)
    MstVendor findByVendorId(@Param("vendorId") Long vendorId);

    @Query(value = "select mb.* from mst_vendor mb where lower(mb.nama_vendor) = lower(:namaVendor) order by mb.vendor_id asc limit 1",nativeQuery = true)
    MstVendor findByNamaVendor(@Param("namaVendor") String namaVendor);

    @Query(value = "select mb.* from mst_vendor mb order by mb.vendor_id asc ",nativeQuery = true)
    List<MstVendor> findAll();
}
