package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.MstRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<MstRole, Long> {
    @Query(value = "select mr.* from mst_role mr where mr.role_id = :roleId",nativeQuery = true)
    MstRole findByRoleId(@Param("roleId") Long roleId);

    @Query(value = "select mr.* from mst_role mr where mr.role_id = :roleId",nativeQuery = true)
    List<MstRole> findAllByRoleId(@Param("roleId") Long roleId);

    @Query(value = "select mr.* from mst_role mr where lower(mr.role_name) = lower(:roleName) order by mr.role_id asc limit 1",nativeQuery = true)
    MstRole findByRoleName(@Param("roleName") String roleName);

    @Query(value = "select mr.* from mst_role mr order by mr.role_id asc ",nativeQuery = true)
    List<MstRole> findAll();

}
