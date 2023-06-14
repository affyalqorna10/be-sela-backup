package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.MstUserGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends PagingAndSortingRepository<MstUserGroup, Long> {

    @Query(value = "select mb.* from mst_user_groups mb where mb.ug_id= :ugId",nativeQuery = true)
    MstUserGroup findByGroupId(@Param("ugId") Long ugId);

    @Query(value = "select mb.* from mst_user_groups mb where lower(mb.nama_group) = lower(:namaGroup) order by mb.ug_id asc limit 1",nativeQuery = true)
    MstUserGroup findByNamaGroup(@Param("namaGroup") String namaGroup);

    @Query(value = "select mb.* from mst_user_groups mb order by mb.ug_id asc ",nativeQuery = true)
    List<MstUserGroup> findAll();
}
