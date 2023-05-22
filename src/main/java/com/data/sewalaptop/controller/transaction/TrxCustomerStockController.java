package com.data.sewalaptop.controller.transaction;

import com.data.sewalaptop.service.transaction.TrxCustomerStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer_stock")
public class TrxCustomerStockController {
    @Autowired
    private TrxCustomerStockService tcsService;

    @GetMapping("/get_by_id/{csId}")
    public ResponseEntity<?> getByCsId(@PathVariable Long csId){

        return tcsService.getByCsId(csId);
    }

    @GetMapping("/get_by_brand_id/{idBrand}")
    public ResponseEntity<?> getByBrandId(@PathVariable Long idBrand){

        return tcsService.getByBrandId(idBrand);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllNoPageble(){

        return tcsService.getAllNoPageble();
    }
}
