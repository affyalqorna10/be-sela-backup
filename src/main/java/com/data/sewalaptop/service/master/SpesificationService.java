package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.*;
import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.model.master.*;
import com.data.sewalaptop.repository.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

import static com.data.sewalaptop.common.Checker.*;

@Service
public class SpesificationService {
    @Autowired
    private SpesificationRepository spekRepo;

    public ResponseEntity<?> saveSpesification(MstSpesifikasiDTO requestDTO){
        if (requestDTO.getSpekId() == null){
            return createSpesification(requestDTO);
        } else {
            return updateSpesification(requestDTO);
        }
    }

    public ResponseEntity<?> deleteSpesification(Long spekId){
        ResponseDTO response = new ResponseDTO();
        MstSpesifikasi spek = spekRepo.findBySpekId(spekId);
        if (spek == null){
            response.setCode("204");
            response.setMessage("Brand ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        spekRepo.delete(spek);

        response.setCode("200");
        response.setData(null);
        response.setMessage("Brand id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByBrandId(Long brandId){
        ResponseDTO response = new ResponseDTO();
        MstSpesifikasi spek = spekRepo.findByBrandId(brandId);

        response.setCode("200");
        response.setData(spek);
        response.setMessage("Get Data by brand id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstSpesifikasi> spekList = spekRepo.findAll();

        response.setCode("200");
        response.setData(spekList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createSpesification(MstSpesifikasiDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        MstSpesifikasi spekEntity = new MstSpesifikasi();
        MstSpesifikasi spek = spekRepo.findByBrandId(requestDTO.getBrandId());
        if (spek == null) {
            if (isNullStr(requestDTO.getStorage())) {
                if (isNullStr(requestDTO.getProcessor())) {
                    if (isNullStr(requestDTO.getRam())) {
                        if (isNullStr(requestDTO.getGraphicCard())) {
                            spekEntity.setBrandId(requestDTO.getBrandId());
                            spekEntity.setStorage(requestDTO.getStorage());
                            spekEntity.setProcessor(requestDTO.getProcessor());
                            spekEntity.setRam(requestDTO.getRam());
                            spekEntity.setGraphicCard(requestDTO.getGraphicCard());

                            spekRepo.save(spekEntity);

                            response.setCode("201");
                            response.setData(null);
                            response.setMessage("Spesification has been saved successfully");
                            return new ResponseEntity<>(response, HttpStatus.CREATED);
                        }
                        response.setCode("204");
                        response.setMessage("Graphic Card cannot be empty");
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    response.setCode("204");
                    response.setMessage("Ram cannot be empty");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                response.setCode("204");
                response.setMessage("Processor cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("Storage cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setMessage("Data Spesification already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> updateSpesification(MstSpesifikasiDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        MstSpesifikasi spekEntity = new MstSpesifikasi();
        MstSpesifikasi spekList = spekRepo.findBySpekId(requestDTO.getSpekId());

        if (spekList == null) {
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        MstSpesifikasi spek = spekRepo.findBySpekId(requestDTO.getSpekId());
        if (requestDTO.getSpekId() == null) {
            spekEntity.setSpekId(spek.getSpekId());
        } else {
            spekEntity.setSpekId(requestDTO.getSpekId());
        }

        if (isNullStr(requestDTO.getStorage())) {
            spekEntity.setBrandId(requestDTO.getBrandId());
        } else {
            spekEntity.setBrandId(spek.getBrandId());
        }

        if (isNullStr(requestDTO.getProcessor())) {
            spekEntity.setProcessor(spek.getProcessor());
        } else {
            spekEntity.setProcessor(requestDTO.getProcessor());
        }

        if (isNullStr(requestDTO.getRam())) {
            spekEntity.setRam(requestDTO.getRam());
        } else {
            spekEntity.setRam(spek.getRam());
        }

        if (isNullStr(requestDTO.getGraphicCard())) {
            spekEntity.setGraphicCard(requestDTO.getGraphicCard());
        } else {
            spekEntity.setGraphicCard(spek.getGraphicCard());
        }

        spekRepo.save(spekEntity);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Spesification has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
