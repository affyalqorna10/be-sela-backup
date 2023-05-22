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

import static com.data.sewalaptop.common.Checker.*;

@Service
public class TrxDetailStockService {
    @Autowired
    private TrxDetailStockRepository tdsRepo;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SpesificationService spekService;

    @Autowired
    private StockService stockService;

    @Autowired
    private BrandRepository brandRepo;

    @Autowired
    private SpesificationRepository spekRepo;

    @Autowired
    private StockRepository stockRepo;

    public ResponseEntity<?> saveDetailStock(TrxDetailStockDTO requestDTO){
        if (requestDTO.getBrandId() == null){
            return createDetailStock(requestDTO);
        }
        return UpdateDetailStock(requestDTO);
    }

    private ResponseEntity<?> createDetailStock(TrxDetailStockDTO requestDTO) {
        int status =0;
        // Brand save
        MstBrandsDTO brandDTO = new MstBrandsDTO();
        brandDTO.setBrandName(requestDTO.getBrandName());
        status = brandService.saveBrand(brandDTO).getStatusCodeValue();

        // Spesifikasi save
        MstBrands brand = brandRepo.findByBrandName(requestDTO.getBrandName());
        MstSpesifikasiDTO spekDTO = new MstSpesifikasiDTO();
        spekDTO.setBrandId(brand.getBrandId());
        spekDTO.setStorage(requestDTO.getStorage());
        spekDTO.setProcessor(requestDTO.getProcessor());
        spekDTO.setRam(requestDTO.getRam());
        spekDTO.setGraphicCard(requestDTO.getGraphicCard());
        status = spekService.saveSpesification(spekDTO).getStatusCodeValue();

        // Stock Save
        MstSpesifikasi spek = spekRepo.findByBrandId(brand.getBrandId());
        MstStockDTO stockDTO = new MstStockDTO();
        stockDTO.setStockQty(requestDTO.getStock());
        stockDTO.setBrandId(requestDTO.getBrandId());
        status = stockService.saveStock(stockDTO).getStatusCodeValue();

        ResponseDTO response = new ResponseDTO();
        if (status == 201){
            response.setCode("201");
            response.setData(null);
            response.setMessage("Brand has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        response.setCode("409");
        response.setData(null);
        response.setMessage("data already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> UpdateDetailStock(TrxDetailStockDTO requestDTO){
        int status =0;
        // Brand save
        MstBrandsDTO brandDTO = new MstBrandsDTO();
        brandDTO.setBrandId(requestDTO.getBrandId());
        brandDTO.setBrandName(requestDTO.getBrandName());
        status = brandService.saveBrand(brandDTO).getStatusCodeValue();

        // Spesifikasi save
        MstSpesifikasi spek = spekRepo.findByBrandId(requestDTO.getBrandId());
        MstSpesifikasiDTO spekDTO = new MstSpesifikasiDTO();
        spekDTO.setSpekId(spek.getSpekId());
        spekDTO.setBrandId(requestDTO.getBrandId());
        spekDTO.setStorage(requestDTO.getStorage());
        spekDTO.setProcessor(requestDTO.getProcessor());
        spekDTO.setRam(requestDTO.getRam());
        spekDTO.setGraphicCard(requestDTO.getGraphicCard());
        status = spekService.saveSpesification(spekDTO).getStatusCodeValue();

        // Stock Save
        MstStock stock = stockRepo.findByBrandId(requestDTO.getBrandId());
        MstStockDTO stockDTO = new MstStockDTO();
        stockDTO.setStockQty(Math.toIntExact(requestDTO.getStock()));
        stockDTO.setBrandId(requestDTO.getBrandId());
//        status = stockService.saveStock(stockDTO).getStatusCodeValue();

        ResponseDTO response = new ResponseDTO();
        if (status == 201){
            response.setCode("201");
            response.setData(null);
            response.setMessage("Brand has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        response.setCode("409");
        response.setData(null);
        response.setMessage("data already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> getByBrandId(Long brandId){
        ResponseDTO response = new ResponseDTO();
        TrxDetailStock detailStock = tdsRepo.findByBrandId(brandId);

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
