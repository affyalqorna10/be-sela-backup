package com.data.sewalaptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.sewalaptop.model.User;


@Repository
public interface Userrepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
