package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SpesificationRepository extends PagingAndSortingRepository<MstSpesifikasi, Long> {
    @Query(value = "select ms.* from mst_spesifikasi ms where ms.spek_id= :spekId order by ms.spek_id asc limit 1",nativeQuery = true)
    MstSpesifikasi findBySpekId(@Param("spekId") Long spekId);

    @Query(value = "select ms.* from mst_spesifikasi ms where ms.device_id= :deviceId order by ms.device_id asc limit 1",nativeQuery = true)
    MstSpesifikasi findByDeviceId(@Param("deviceId") Long deviceId);

    @Query(value = "select ms.* from mst_spesifikasi ms where ms.device_id= :deviceId",nativeQuery = true)
    List<MstSpesifikasi> findAllByDeviceId(@Param("deviceId") Long deviceId);

    @Query(value = "SELECT * FROM mst_spesifikasi AS spek LEFT JOIN mst_device AS device ON spek.device_id = device.device_id",nativeQuery = true)
    List<MstSpesifikasi> findAll();

}
