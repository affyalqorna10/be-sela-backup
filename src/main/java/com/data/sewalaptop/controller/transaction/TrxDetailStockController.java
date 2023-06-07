package com.data.sewalaptop.controller.transaction;

import com.data.sewalaptop.dto.transaction.TrxDetailStockDTO;
import com.data.sewalaptop.service.master.*;
import com.data.sewalaptop.service.transaction.TrxDetailStockService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/detail_stock")
public class TrxDetailStockController {
    @Autowired
    private TrxDetailStockService tdsService;

    @PostMapping("/save")
    public ResponseEntity<?> getByBrandId(@RequestBody TrxDetailStockDTO requestDTO){

        return tdsService.saveDetailStock(requestDTO);
    }

    @GetMapping("/get_by_brand_id/{idBrand}")
    public ResponseEntity<?> getByBrandId(@PathVariable Long idBrand){

        return tdsService.getByBrandId(idBrand);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllNoPageble(){

        return tdsService.getAllNoPageble();
    }
}
