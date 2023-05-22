package com.data.sewalaptop.controller.transaction;

import com.data.sewalaptop.service.master.*;
import com.data.sewalaptop.service.transaction.TrxDetailStockService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/detail_stock")
public class TrxDetailStockController {
    @Autowired
    private BrandService brandService;

    @Autowired
    private SpesificationService spekService;

    @Autowired
    private StockService stockService;

    @Autowired
    private TrxDetailStockService trxDetailStockService;

    @GetMapping("/get_By/{idBrand}")
    public ResponseEntity<?> getByBrandId(@PathVariable Long idBrand){

        return trxDetailStockService.getByBrandId(idBrand);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllNoPageble(){

        return trxDetailStockService.getAllNoPageble();
    }
}
