package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.service.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/save")
    public ResponseEntity<?> saveStock(@RequestBody MstStockDTO request){

        return stockService.saveStock(request);
    }

    @PostMapping("/delete/{idStock}")
    public ResponseEntity<?> saveStock(@PathVariable Long idStock){

        return stockService.deleteStock(idStock);
    }

    @GetMapping("/barcode")
    public byte[] saveStock(@RequestBody String codeQr){

        return stockService.generateBarcode(codeQr);
    }
}
