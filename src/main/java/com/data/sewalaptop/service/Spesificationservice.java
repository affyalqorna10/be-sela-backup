package com.data.sewalaptop.service;

import java.util.List;

import com.data.sewalaptop.model.Spesification;

public interface Spesificationservice {
    
    public List<Spesification> index();
    
    public Spesification insert(Spesification spesification);

    public Spesification show(Long id);

    public Spesification update(Long id, Spesification spesification);

    public Spesification delete(Long id);
}
