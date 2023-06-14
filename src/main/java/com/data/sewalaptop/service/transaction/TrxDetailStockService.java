package com.data.sewalaptop.service.transaction;

import com.data.sewalaptop.common.*;
import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.dto.transaction.TrxDetailStockDTO;
import com.data.sewalaptop.model.master.*;
import com.data.sewalaptop.model.transaction.*;
import com.data.sewalaptop.repository.master.*;
import com.data.sewalaptop.repository.transaction.*;
import com.data.sewalaptop.service.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TrxDetailStockService {
    @Autowired
    private TrxDetailStockRepository tdsRepo;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SpesificationService spekService;

    @Autowired
    private StockService stockService;

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private SpesificationRepository spekRepo;

    @Autowired
    private StockRepository stockRepo;

    public ResponseEntity<?> saveDetailStock(TrxDetailStockDTO requestDTO){
        if (requestDTO.getDeviceId() == null){
            return createDetailStock(requestDTO);
        }
        return UpdateDetailStock(requestDTO);
    }

    private ResponseEntity<?> createDetailStock(TrxDetailStockDTO requestDTO) {
        int status =0;
        // Device save
        MstDevicesDTO devicesDTO = new MstDevicesDTO();
        devicesDTO.setDeviceName(requestDTO.getDeviceName());
        status = deviceService.saveDevice(devicesDTO).getStatusCodeValue();

        // Spesifikasi save
        MstDevices device = deviceRepo.findByDeviceName(requestDTO.getDeviceName());
        MstSpesifikasiDTO spekDTO = new MstSpesifikasiDTO();
        spekDTO.setDeviceId(device.getDeviceId());
        spekDTO.setStorage(requestDTO.getStorage());
        spekDTO.setProcessor(requestDTO.getProcessor());
        spekDTO.setRam(requestDTO.getRam());
        spekDTO.setGraphicCard(requestDTO.getGraphicCard());
        status = spekService.saveSpesification(spekDTO).getStatusCodeValue();

        // Stock Save
        MstSpesifikasi spek = spekRepo.findByDeviceId(requestDTO.getDeviceId());
        MstStockDTO stockDTO = new MstStockDTO();
        stockDTO.setStockQty(requestDTO.getStock());
        stockDTO.setDeviceId(requestDTO.getDeviceId());
        status = stockService.saveStock(stockDTO).getStatusCodeValue();

        ResponseDTO response = new ResponseDTO();
        if (status == 201){
            response.setCode("201");
            response.setData(null);
            response.setMessage("Detail Stock has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        response.setCode("409");
        response.setData(null);
        response.setMessage("data already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> UpdateDetailStock(TrxDetailStockDTO requestDTO){
        int status =0;
        // Device save
        MstDevicesDTO devicesDTO = new MstDevicesDTO();
        devicesDTO.setDeviceId(requestDTO.getDeviceId());
        devicesDTO.setDeviceName(requestDTO.getDeviceName());

        status = deviceService.saveDevice(devicesDTO).getStatusCodeValue();

        // Spesifikasi save
        MstSpesifikasi spek = spekRepo.findByDeviceId(requestDTO.getDeviceId());
        MstSpesifikasiDTO spekDTO = new MstSpesifikasiDTO();
        spekDTO.setSpekId(spek.getSpekId());
        spekDTO.setDeviceId(requestDTO.getDeviceId());
        spekDTO.setStorage(requestDTO.getStorage());
        spekDTO.setProcessor(requestDTO.getProcessor());
        spekDTO.setRam(requestDTO.getRam());
        spekDTO.setGraphicCard(requestDTO.getGraphicCard());
        status = spekService.saveSpesification(spekDTO).getStatusCodeValue();

        // Stock Save
        MstStock stock = stockRepo.findByDeviceId(requestDTO.getDeviceId());
        MstStockDTO stockDTO = new MstStockDTO();
        stockDTO.setStockQty(Math.toIntExact(requestDTO.getStock()));
        stockDTO.setDeviceId(requestDTO.getDeviceId());
        status = stockService.saveStock(stockDTO).getStatusCodeValue();

        ResponseDTO response = new ResponseDTO();
        if (status == 201){
            response.setCode("201");
            response.setData(null);
            response.setMessage("Detail Stock has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        response.setCode("409");
        response.setData(null);
        response.setMessage("data already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> getByDeviceId(Long deviceId){
        ResponseDTO response = new ResponseDTO();
        TrxDetailStock detailStock = tdsRepo.findByDeviceId(deviceId);

        response.setCode("200");
        response.setData(detailStock);
        response.setMessage("Get Data Detail Stock Successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getAllNoPageble(){
        ResponseDTO response = new ResponseDTO();
        List<TrxDetailStock> stockList = tdsRepo.findAllNoPageble();

        response.setCode("200");
        response.setData(stockList);
        response.setMessage("Get All Data Detail Stock Successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
