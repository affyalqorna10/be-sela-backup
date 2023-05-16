package com.data.sewalaptop.service;

import java.util.List;

import com.data.sewalaptop.model.Brand;

public interface Brandservice {
    
    public List<Brand> index();

    public Brand insert(Brand brand);

    public Brand show(Long id);

    public Brand Update(Long id, Brand brand);

    public Brand delete(long id);
}
