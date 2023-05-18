package com.data.sewalaptop.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.sewalaptop.dto.Branddto;
import com.data.sewalaptop.dto.SMdto;
import com.data.sewalaptop.model.Brand;
import com.data.sewalaptop.model.Spesification;
import com.data.sewalaptop.model.Stock;
import com.data.sewalaptop.repository.Brandrepository;
import com.data.sewalaptop.repository.Spesificationrepository;
import com.data.sewalaptop.repository.Stockrepository;
import com.data.sewalaptop.service.Brandservice;

@Service
public class Brandserviceimpl implements Brandservice {
    @Autowired Brandrepository brandrepository;
    @Autowired Spesificationrepository spesificationrepository;
    @Autowired Stockrepository stockrepository;

    @Override
    public List<Brand> index() {
        List<Brand> index = brandrepository.findAll();
        return index;
    }

    @Override
    public Brand insert(Branddto brand) {
        Brand brandentity = new Brand();
        brandentity.setId(brand.getId());
        brandentity.setBrand_name(brand.getBrand_name());
        brandentity.setIdmodel(brand.getIdmodel());
        return brandrepository.save(brandentity);
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
    public Brand update(Long id, Branddto brand) {
        Brand data = this.show(id);
        if (data == null) {
            return null;
        }
        data.setIdmodel(brand.getIdmodel());
        data.setBrand_name(brand.getBrand_name());
        return data;
    }

    @Override
    public Brand delete(Long id) {
        Brand data = this.show(id);
        if (data == null) {
            return null;
        }
        brandrepository.delete(data);
        return data;
    }

    @Override
    public SMdto getById(Long id) {
        SMdto responseDto = new SMdto();
        Brand brand = brandrepository.findByBrandId(id);
        if (brand != null) {
        responseDto.setId(brand.getId());
        responseDto.setBrand_name(brand.getBrand_name());
        responseDto.setIdmodel(brand.getIdmodel());
        }
        Spesification spek = spesificationrepository.findByBrandId(id);
        if (spek != null) {
            responseDto.setProcessor(spek.getProcessor());
            responseDto.setRam(spek.getRam());
            responseDto.setStorage(spek.getStorage());
            responseDto.setGraphic_card(spek.getGraphic_card());
        }
        Stock stk = stockrepository.findByBrandId(id);
        if (stk != null) {
            responseDto.setStock(stk.getStock());
        }
        return responseDto;
    }

    @Override
    public List<SMdto> getAll() {
        List<SMdto> getall = new ArrayList<>();
        List<Brand> brandList = brandrepository.findAll();
        brandList.forEach((item)->{
            SMdto responseDto = new SMdto();
            Brand brand = brandrepository.findByBrandId(item.getId());
        if (brand != null) {
            responseDto.setId(brand.getId());
            responseDto.setBrand_name(brand.getBrand_name());
            responseDto.setIdmodel(brand.getIdmodel());
            }
            Spesification spek = spesificationrepository.findByBrandId(item.getId());
            if (spek != null) {
                responseDto.setProcessor(spek.getProcessor());
                responseDto.setRam(spek.getRam());
                responseDto.setStorage(spek.getStorage());
                responseDto.setGraphic_card(spek.getGraphic_card());
            }
            Stock stk = stockrepository.findByBrandId(item.getId());
            if (stk != null) {
                responseDto.setStock(stk.getStock());
            }
            getall.add(responseDto);
        });
        return getall;
    }
}
