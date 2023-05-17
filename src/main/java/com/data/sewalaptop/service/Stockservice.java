package com.data.sewalaptop.service;

import java.util.List;

import com.data.sewalaptop.model.Stock;

public interface Stockservice {
    public List<Stock> index();

    public Stock insert(Stock stock);

    public Stock show(Long id);

    public Stock update(Long id, Stock stock);

    public Stock delete(Long id);
}
