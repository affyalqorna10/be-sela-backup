package com.data.sewalaptop.service.implementation;

import java.util.ArrayList;
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
    public List<Stock> insert(Stockdto stock) {
        List<Stock> listStocks = new ArrayList<>();
        for (int i = 0; i < stock.getStock(); i++) {
            Stock stockentity = new Stock();
            System.out.print("i data ke " + i);
            stockentity.setBrand_id(stock.getBrand_id());
            stockentity.setStatus("aktif");
            stockentity.setCode(String.format("%s",i));
            
            stockrepository.save(stockentity);
            System.out.print("save data " + i); 
            listStocks.add(stockentity);
        }
        return listStocks;
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
        Stock data = stockrepository.findByStockId(id);
        if (data == null) {
            return null;
        }
        data.setBrand_id(stock.getBrand_id());
        data.setCode(data.getCode());
        data.setStatus(data.getStatus());

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
