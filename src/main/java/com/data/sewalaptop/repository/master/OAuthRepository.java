package com.data.sewalaptop.repository.master;

import com.data.sewalaptop.model.master.OAuth;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface OAuthRepository extends PagingAndSortingRepository<OAuth, Long> {
    public Optional<OAuth> findByUserId(Long id);
    public Optional<OAuth> findByUserToken(String userToken);

}
