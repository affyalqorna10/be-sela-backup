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

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private VendorRepository vendorRepo;

    @Autowired
    private VendorService vendorService;

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
            response.setMessage("Brand ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        stockRepo.delete(stock);

        response.setCode("200");
        response.setData(null);
        response.setMessage("Brand id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByStockId(Long stockId){
        ResponseDTO response = new ResponseDTO();
        MstStock stockList = stockRepo.findByStockId(stockId);
        if (stockList == null){
            response.setCode("204");
            response.setMessage("Brand ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(stockList);
        response.setMessage("Get data by stock id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByBrandId(Long brandId){
        ResponseDTO response = new ResponseDTO();
        List<MstStock> stockList = stockRepo.findAllByBrandId(brandId);
        if (stockList == null){
            response.setCode("204");
            response.setMessage("Brand ID not found");
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
            response.setMessage("Brand ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(stockList);
        response.setMessage("Get All data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createStock(MstStockDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        List<MstStock> stockList = stockRepo.findAllByBrandId(requestDTO.getBrandId());
        MstVendor vendor = vendorRepo.findByVendorId(requestDTO.getVendorId());
        if (stockList.size() > 0) {
            response.setCode("409");
            response.setMessage("data already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        if (requestDTO.getBrandId() == null) {
            response.setCode("204");
            response.setMessage("Brand Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (vendor.getVendorId() == null){
            response.setCode("204");
            response.setMessage("Brand Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (requestDTO.getStockQty() == null) {
            response.setCode("204");
            response.setMessage("Stock Qty cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for (int i = 0; i < requestDTO.getStockQty(); i++) {
            MstStock stockEntity = new MstStock();

            stockEntity.setBrandId(requestDTO.getBrandId());
            stockEntity.setVendorId(requestDTO.getVendorId());
            stockEntity.setCodeQr(UUID.randomUUID().toString());
            stockEntity.setStatus("ACTIVE");

            stockRepo.save(stockEntity);
        }

        response.setCode("201");
        response.setData(null);
        response.setMessage("Brand has been saved successfully");
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

        if (isNullStr(requestDTO.getCodeQr())){
            stockEntity.setCodeQr(requestDTO.getCodeQr());
        } else {
            stockEntity.setCodeQr(UUID.randomUUID().toString());
        }

        if (isNullStr(requestDTO.getStatus())){
            stockEntity.setStatus(requestDTO.getStatus());
        } else {
            stockEntity.setStatus(stockList.getStatus());
        }

        if (requestDTO.getBrandId() == null){
            stockEntity.setStockId(stockList.getBrandId());
        } else {
            stockEntity.setStockId(requestDTO.getBrandId());
        }

        if (requestDTO.getVendorId() == null){
            stockEntity.setStockId(stockList.getVendorId());
        }else{
            stockEntity.setStockId(requestDTO.getVendorId());
        }

        stockRepo.save(stockEntity);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Brand has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
