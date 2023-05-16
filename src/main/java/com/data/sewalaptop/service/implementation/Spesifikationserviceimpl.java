package com.data.sewalaptop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.sewalaptop.model.Spesifikation;
import com.data.sewalaptop.repository.Spesifikationrepository;
import com.data.sewalaptop.service.Spesifikationservice;

@Service
public class Spesifikationserviceimpl implements Spesifikationservice {
    @Autowired 
    Spesifikationrepository spesifikationrepository;

    @Override
    public List<Spesifikation> index() {
        List<Spesifikation> index = spesifikationrepository.findAll();
        return index;
    }

    @Override
    public Spesifikation insert(Spesifikation spesifikation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public Spesifikation show(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }

    @Override
    public Spesifikation update(Long id, Spesifikation spesifikation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Spesifikation delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
