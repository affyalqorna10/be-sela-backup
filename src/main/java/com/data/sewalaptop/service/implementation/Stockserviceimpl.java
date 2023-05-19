package com.data.sewalaptop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.sewalaptop.dto.Stockdto;
import com.data.sewalaptop.model.Stock;
import com.data.sewalaptop.repository.Stockrepository;
import com.data.sewalaptop.service.Stockservice;

@Service
public class Stockserviceimpl implements Stockservice {
    @Autowired Stockrepository stockrepository;

    @Override
    public List<Stock> index() {
        List<Stock> index = stockrepository.findAll();
        return index;
    }

    @Override
    public Stock insert(Stockdto stock) {
        Stock stockentity = new Stock();
        stockentity.setBrand_id(stock.getBrand_id());
        stockentity.setStock(stock.getStock());
        return stockrepository.save(stockentity);
    }

    @Override
    public Stock show(Long id) {
        Optional<Stock> bOptional = stockrepository.findById(id);
        if (bOptional.isPresent()) {
            return bOptional.get();
        }
        return null;
    }

    @Override
    public Stock update(Long id, Stockdto stock) {
        Stock data = this.show(id);
        if (data == null) {
            return null;
        }
        data.setBrand_id(stock.getBrand_id());
        data.setStock(stock.getStock());
        return data;
    }

    @Override
    public Stock delete(Long id) {
        Stock data = this.show(id);
        if (data == null) {
            return null;
        }
        stockrepository.delete(data);
        return data;
    }
    
}
