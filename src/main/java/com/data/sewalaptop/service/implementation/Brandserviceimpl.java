package com.data.sewalaptop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.sewalaptop.model.Brand;
import com.data.sewalaptop.repository.Brandrepository;
import com.data.sewalaptop.service.Brandservice;

@Service
public class Brandserviceimpl implements Brandservice {
    @Autowired
    Brandrepository brandrepository;

    @Override
    public List<Brand> index() {
        List<Brand> index = brandrepository.findAll();
        return index;
    }

    @Override
    public Brand insert(Brand brand) {
        return brandrepository.save(brand);
    }

    @Override
    public Brand show(Long id) {
        Optional<Brand> bOptional = brandrepository.findById(id);
        if (bOptional.isPresent()) {
            return bOptional.get();
        }
        return null;
    }

    @Override
    public Brand delete(long id) {
        Brand data = this.show(id);
        if (data == null) {
            return null;
        }
        brandrepository.delete(data);
        return data;
    }

    @Override
    public Brand update(Long id, Brand brand) {
        Brand data = this.show(id);
        if (data == null) {
            return null;
        }
        data.setName_brand(brand.getName_brand());
        data.setModel(brand.getModel());
        return data;
    }
}
