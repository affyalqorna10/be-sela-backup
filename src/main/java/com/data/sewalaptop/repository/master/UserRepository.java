package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<MstUser, Long> {
    @Query(value = "select mu.* from mst_user mu where lower(mu.user_email)= lower(:userMail)",nativeQuery = true)
    MstUser findByEmail(@Param("userMail") String userMail);
}
