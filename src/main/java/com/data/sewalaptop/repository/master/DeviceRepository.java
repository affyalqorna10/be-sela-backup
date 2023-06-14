package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DeviceRepository extends PagingAndSortingRepository<MstDevices, Long> {
    @Query(value = "select mb.* from mst_device mb where mb.device_id = :deviceId",nativeQuery = true)
    MstDevices findByDeviceId(@Param("deviceId") Long deviceId);

    @Query(value = "select mb.* from mst_device mb where mb.device_id = :deviceId",nativeQuery = true)
    List<MstDevices> findAllByDeviceId(@Param("deviceId") Long deviceId);

    @Query(value = "select mb.* from mst_device mb where lower(mb.device_name) = lower(:deviceName) order by mb.deviceId asc limit 1",nativeQuery = true)
    MstDevices findByDeviceName(@Param("deviceName") String deviceName);

    @Query(value = "select mb.* from mst_device mb order by mb.device_id asc ",nativeQuery = true)
    List<MstDevices> findAll();

}
