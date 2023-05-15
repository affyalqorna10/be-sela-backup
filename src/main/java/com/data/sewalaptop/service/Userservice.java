package com.data.sewalaptop.service;

import java.util.List;

import com.data.sewalaptop.dto.Userdto;
import com.data.sewalaptop.model.User;

public interface Userservice {

    public List<User> index();
    
    public User register(User user);

    public User show(Long id);

    public User update(Long id, User user);

    public User delete(Long id);

    public Userdto login(User body) throws Exception;
}