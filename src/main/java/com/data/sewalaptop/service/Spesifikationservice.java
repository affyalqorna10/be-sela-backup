package com.data.sewalaptop.service;

import java.util.List;

import com.data.sewalaptop.model.Spesifikation;

public interface Spesifikationservice {
    public List<Spesifikation> index();
    
    public Spesifikation insert(Spesifikation spesifikation);

    public Spesifikation show(Long id);

    public Spesifikation update(Long id, Spesifikation spesifikation);

    public Spesifikation delete(Long id);
}
