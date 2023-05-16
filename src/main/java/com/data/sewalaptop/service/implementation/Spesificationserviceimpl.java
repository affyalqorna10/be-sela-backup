package com.data.sewalaptop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.sewalaptop.model.Spesification;
import com.data.sewalaptop.repository.Spesificationrepository;
import com.data.sewalaptop.service.Spesificationservice;

@Service
public class Spesificationserviceimpl implements Spesificationservice {
    @Autowired Spesificationrepository spesificationrepository;

    @Override
    public List<Spesification> index() {
        List<Spesification> index = spesificationrepository.findAll();
        return index;
    }

    @Override
    public Spesification insert(Spesification spesification) {
        return spesificationrepository.save(spesification);
    }

    @Override
    public Spesification show(Long id) {
        Optional<Spesification> bOptional = spesificationrepository.findById(id);
        if (bOptional.isPresent()) {
            return bOptional.get();
        }
        return null;
    }

    @Override
    public Spesification update(Long id, Spesification spesification) {
        Spesification data = this.show(id);
        if (data == null) {
            return null;
        }
        data.setBrand_id(spesification.getBrand_id());
        data.setRam(spesification.getRam());
        data.setStorage(spesification.getStorage());
        data.setGraphic_card(spesification.getGraphic_card());
        return data;
    }

    @Override
    public Spesification delete(Long id) {
        Spesification data = this.show(id);
        if (data == null) {
            return null;
        }
        spesificationrepository.delete(data);
        return data;
    }
    
}
