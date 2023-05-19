package com.data.sewalaptop.service;

import java.util.List;

import com.data.sewalaptop.dto.Stockdto;
import com.data.sewalaptop.model.Stock;

public interface Stockservice {
    public List<Stock> index();

    public Stock insert(Stockdto stock);

    public Stock show(Long id);

    public Stock update(Long id, Stockdto stock);

    public Stock delete(Long id);
}
