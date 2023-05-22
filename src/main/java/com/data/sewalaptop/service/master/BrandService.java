package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.*;
import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.model.master.*;
import com.data.sewalaptop.repository.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Objects;

import static com.data.sewalaptop.common.Checker.*;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepo;

    public ResponseEntity<?> saveBrand(MstBrandsDTO requestDTO) {
        if (requestDTO.getBrandId() == null) {
            return createBrand(requestDTO);
        }
        return updateBrand(requestDTO);
    }

    public ResponseEntity<?> deleteBrand(Long brandId){
        ResponseDTO response = new ResponseDTO();
        MstBrands brands = brandRepo.findByBrandId(brandId);
        if (brands == null){
            response.setCode("204");
            response.setMessage("Brand ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        brandRepo.delete(brands);

        response.setCode("200");
        response.setData(null);
        response.setMessage("Brand id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByBrandId(Long brandId){
        ResponseDTO response = new ResponseDTO();
        MstBrands brands = brandRepo.findByBrandId(brandId);

        response.setCode("200");
        response.setData(brands);
        response.setMessage("Get Data By Brand Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllBrand(){
        ResponseDTO response = new ResponseDTO();
        List<MstBrands> brandsList = brandRepo.findAll();

        response.setCode("200");
        response.setData(brandsList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createBrand(MstBrandsDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstBrands brands = brandRepo.findByBrandName(requestDTO.getBrandName());
        if (brands == null) {
            MstBrands brand = new MstBrands();
            // Validate Brand Name null && string only
            if (isNullStr(requestDTO.getBrandName())) {
                if (validStrOnly(requestDTO.getBrandName())) {

                    brand.setBrandName(requestDTO.getBrandName().toUpperCase());
                    brandRepo.save(brand);

                    response.setCode("201");
                    response.setData(null);
                    response.setMessage("Brand has been saved successfully");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
                response.setCode("500");
                response.setMessage("Brands cannot have numbers");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("Brand name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("409");
        response.setData(null);
        response.setMessage("Brand already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> updateBrand(MstBrandsDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstBrands brands = brandRepo.findByBrandId(requestDTO.getBrandId());

        if (Objects.equals(brands.getBrandId(), requestDTO.getBrandId())){
            MstBrands brand = new MstBrands();
            if (isNullStr(requestDTO.getBrandName())){
                if (validStrOnly(requestDTO.getBrandName())){
                    brand.setBrandId(requestDTO.getBrandId());
                    brand.setBrandName(requestDTO.getBrandName());

                    brandRepo.save(brand);

                    response.setCode("201");
                    response.setData(null);
                    response.setMessage("Brand has been saved successfully");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }

                response.setCode("500");
                response.setMessage("Brands cannot have numbers");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.setCode("204");
            response.setMessage("Brand name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("409");
        response.setData(null);
        response.setMessage("Brand already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
