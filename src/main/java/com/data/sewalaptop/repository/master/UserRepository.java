package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<MstUser, Long> {
    @Query(value = "select mu.* from mst_user mu where lower(mu.user_email)= lower(:userEmail)",nativeQuery = true)
    MstUser findByEmail(@Param("userEmail") String userEmail);

    @Query(value = "select ms.* from mst_user ms where ms.user_id= :userId order by ms.user_id asc limit 1",nativeQuery = true)
    MstUser findByUserId(@Param("userId") Long userId);

    @Query(value = "select mb.* from mst_user_groups mb where mb.ug_id= :ugId",nativeQuery = true)
    MstUserGroup findByGroupId(@Param("ugId") Long ugId);

    @Query(value = "select ms.* from mst_user ms order by ms.user_id asc ",nativeQuery = true)
    List<MstUser> findAll();

    @Query(value = "select mu.* from mst_user mu where lower(mu.NIK)= lower(:NIK)",nativeQuery = true)
    MstUser findByNIK(@Param("NIK") String NIK);

    @Query(value = "select ms.* from mst_divisis ms where ms.divisi_id = :divisiId",nativeQuery = true)
    MstDivisi findByDivisiId(@Param("divisiId") Long divisiId);

    public MstUser findByToken(String token);
}
