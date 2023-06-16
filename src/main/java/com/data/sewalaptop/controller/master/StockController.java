package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.service.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveStock(@RequestHeader Map<String,String> header, @RequestBody MstStockDTO request){

        jwtService.filter(header);
        return stockService.saveStock(request);
    }

    @PostMapping("/delete/{idStock}")
    public ResponseEntity<?> deleteStock(@RequestHeader Map<String,String> header, @PathVariable Long idStock){

        jwtService.filter(header);
        return stockService.deleteStock(idStock);
    }

    @GetMapping("/get_by_brandId/{deviceId}")
    public ResponseEntity<?> getAllByDeviceId(@RequestHeader Map<String,String> header, @PathVariable Long deviceId){

        jwtService.filter(header);
        return stockService.getByDeviceId(deviceId);
    }

    @GetMapping("/get_by/{stockId}")
    public ResponseEntity<?> getByStockId(@RequestHeader Map<String,String> header, @PathVariable Long stockId){

        jwtService.filter(header);
        return stockService.getByStockId(stockId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return stockService.getAll();
    }

}
