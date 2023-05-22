package com.data.sewalaptop.service.transaction;

import com.data.sewalaptop.common.*;
import com.data.sewalaptop.model.transaction.*;
import com.data.sewalaptop.repository.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TrxDetailStockService {
    @Autowired
    private TrxDetailStockRepository trxDetailStockRepo;

    public ResponseEntity<?> getByBrandId(Long brandId){
        ResponseDTO response = new ResponseDTO();
        TrxDetailStock detailStock = trxDetailStockRepo.findByBrandId(brandId);

        response.setCode("200");
        response.setData(detailStock);
        response.setMessage("Get Data Detail Stock Successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getAllNoPageble(){
        ResponseDTO response = new ResponseDTO();
        List<TrxDetailStock> stockList = trxDetailStockRepo.findAllNoPageble();

        response.setCode("200");
        response.setData(stockList);
        response.setMessage("Get All Data Detail Stock Successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
