package com.data.sewalaptop.controller.transaction;

import com.data.sewalaptop.dto.transaction.TrxDetailStockDTO;
import com.data.sewalaptop.service.master.*;
import com.data.sewalaptop.service.transaction.TrxDetailStockService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/detail_stock")
public class TrxDetailStockController {
    @Autowired
    private TrxDetailStockService tdsService;

    @PostMapping("/save")
    public ResponseEntity<?> saveDetailStock(@RequestBody TrxDetailStockDTO requestDTO){

        return tdsService.saveDetailStock(requestDTO);
    }

    @GetMapping("/get_by_device_id/{deviceId}")
    public ResponseEntity<?> getByDeviceId(@PathVariable Long deviceId){

        return tdsService.getByDeviceId(deviceId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllNoPageble(){

        return tdsService.getAllNoPageble();
    }
}
