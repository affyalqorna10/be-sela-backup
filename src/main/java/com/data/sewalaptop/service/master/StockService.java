package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.*;
import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.model.master.*;
import com.data.sewalaptop.repository.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import net.glxn.qrgen.javase.QRCode;

import java.io.ByteArrayOutputStream;
import java.util.*;

import static com.data.sewalaptop.common.Checker.*;
import static java.util.Objects.isNull;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private SpesificationRepository spesificationRepo;

    @Autowired
    private SpesificationService spesificationService;

    @Autowired
    private DeviceService deviceService;

    public ResponseEntity<?> saveStock(MstStockDTO requestDTO){
        if (requestDTO.getStockId() == null){
            return createStock(requestDTO);
        } else {
            return updateStock(requestDTO);
        }
    }

    public ResponseEntity<?> deleteStock(Long stockId){
        ResponseDTO response = new ResponseDTO();
        MstStock stock = stockRepo.findByStockId(stockId);
        if (stock == null){
            response.setCode("204");
            response.setMessage("Stock ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        stockRepo.delete(stock);

        response.setCode("200");
        response.setData(null);
        response.setMessage("Stock id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByStockId(Long stockId){
        ResponseDTO response = new ResponseDTO();
        MstStock stockList = stockRepo.findByStockId(stockId);
        if (stockList == null){
            response.setCode("204");
            response.setMessage("Stock ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(stockList);
        response.setMessage("Get data by stock id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByDeviceId(Long deviceId){
        ResponseDTO response = new ResponseDTO();
        List<MstStock> stockList = stockRepo.findAllByDeviceId(deviceId);
        if (stockList == null){
            response.setCode("204");
            response.setMessage("Device ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(stockList);
        response.setMessage("Get All data by brand id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstStock> stockList = stockRepo.findAll();
        if (stockList == null){
            response.setCode("204");
            response.setMessage("Device ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(stockList);
        response.setMessage("Get All data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createStock(MstStockDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        MstDevices devices = deviceRepo.findByDeviceId(requestDTO.getDeviceId());
        MstSpesifikasi spesifikasi = spesificationRepo.findBySpekId(requestDTO.getSpekId());

        if (devices.getDeviceId() == null){
          response.setCode("204");
          response.setMessage("Device Id cannot be empty");
          return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (spesifikasi.getSpekId() == null){
            response.setCode("204");
            response.setMessage("Spesifikasi Id Cannot be empty");
        }

        MstStock stockEntity = new MstStock();
        MstStock stock = stockRepo.findByStockId(requestDTO.getStockId());

        if (stock == null){
            stockEntity.setDeviceId(requestDTO.getDeviceId());
            stockEntity.setSpekId(requestDTO.getSpekId());
            stockEntity.setQty(requestDTO.getQty());

            stockRepo.save(stockEntity);
        }
        response.setCode("201");
        response.setData(null);
        response.setMessage("Stock has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> updateStock(MstStockDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstStock stockEntity = new MstStock();
        MstStock stockList = stockRepo.findByStockId(requestDTO.getStockId());
        if (stockList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (requestDTO.getStockId() == null){
            stockEntity.setStockId(stockList.getStockId());
        } else {
            stockEntity.setStockId(requestDTO.getStockId());
        }

        if (requestDTO.getDeviceId() == null){
            stockEntity.setStockId(stockList.getDeviceId());
        } else {
            stockEntity.setStockId(requestDTO.getDeviceId());
        }

        if (requestDTO.getSpekId() == null){
            stockEntity.setSpekId(stockList.getSpekId());
        }else{
            stockEntity.setSpekId(stockList.getSpekId());
        }

        if (isNull(requestDTO.getQty())){
            stockEntity.setQty(stockList.getQty());
        }else{
            stockEntity.setQty(stockList.getQty());
        }

        stockRepo.save(stockEntity);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Stock has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
