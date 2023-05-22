package com.data.sewalaptop.service;

import java.util.List;

import com.data.sewalaptop.dto.Branddto;
import com.data.sewalaptop.dto.Fiturdetaildto;
import com.data.sewalaptop.dto.SMdto;
import com.data.sewalaptop.model.Brand;

public interface Brandservice {
    
    public List<Brand> index();
    
    public Brand insert(Branddto brand);

    public List<SMdto> getAll();

    public List<Fiturdetaildto> getList();

    public Brand show(Long id);

    public Brand update(Long id, Branddto brand);

    public Brand delete(Long id);

    public SMdto getById(Long id);

}
