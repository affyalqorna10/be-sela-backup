package com.data.sewalaptop.service.transaction;

import com.data.sewalaptop.common.*;
import com.data.sewalaptop.model.transaction.*;
import com.data.sewalaptop.repository.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class TrxCustomerStockService {
    @Autowired
    private TrxCustomerStockRepository tcsRepo;

    public ResponseEntity<?> getByCsId(Long csId){
        ResponseDTO response = new ResponseDTO();
        TrxCustomerStock cs = tcsRepo.findByCsId(csId);

        response.setCode("200");
        response.setData(cs);
        response.setMessage("Get Data Detail Stock Successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getByBrandId(Long brandId){
        ResponseDTO response = new ResponseDTO();
        List<TrxCustomerStock> customerStocks = tcsRepo.findByBrandId(brandId);

        response.setCode("200");
        response.setData(customerStocks);
        response.setMessage("Get Data Detail Stock Successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getAllNoPageble(){
        ResponseDTO response = new ResponseDTO();
        List<TrxCustomerStock> customerStockList = tcsRepo.findAllNoPageble();

        response.setCode("200");
        response.setData(customerStockList);
        response.setMessage("Get All Data Detail Stock Successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
