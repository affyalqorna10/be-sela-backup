package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.MstDivisiDTO;
import com.data.sewalaptop.model.master.MstDivisi;
import com.data.sewalaptop.repository.master.DivisiRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.data.sewalaptop.common.Checker.isNullStr;
import static com.data.sewalaptop.common.Checker.validStrOnly;

@Service
public class DivisiService {
    @Autowired
    private DivisiRespository divRepo;

    public ResponseEntity<?> saveDivisi(MstDivisiDTO requestDTO) {
        if (requestDTO.getDivisiId() == null) {
            return createDivisi(requestDTO);
        } else {
            return updateDivisi(requestDTO);
        }
    }

    public ResponseEntity<?> deleteDivisi(long divisiId) {
        ResponseDTO response = new ResponseDTO();
        MstDivisi divisi = divRepo.findByDivisiId(divisiId);
        if (divisi == null) {
            response.setCode("204");
            response.setMessage("Divisi ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        divRepo.delete(divisi);

        response.setCode("200");
        response.setData(null);
        response.setMessage("Divisi id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByDivisiId(Long divisiId) {
        ResponseDTO response = new ResponseDTO();
        MstDivisi divisi = divRepo.findByDivisiId(divisiId);
        if (divisi == null) {
            response.setCode("204");
            response.setMessage("Divisi ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(divisi);
        response.setMessage("Get Data by divisi id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllDivisi() {
        ResponseDTO response = new ResponseDTO();
        List<MstDivisi> divisiList = divRepo.findAll();

        response.setCode("200");
        response.setData(divisiList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createDivisi(MstDivisiDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        MstDivisi divisis = divRepo.findByDivisiName(requestDTO.getNamaDivisi());
        if (divisis == null) {
            MstDivisi divisi = new MstDivisi();
            // Validate Divisi Name null && string only
            if (isNullStr(requestDTO.getNamaDivisi())) {
                if (validStrOnly(requestDTO.getNamaDivisi())) {

                    divisi.setNamaDivisi(requestDTO.getNamaDivisi().toUpperCase());
                    divRepo.save(divisi);

                    response.setCode("201");
                    response.setData(null);
                    response.setMessage("Divisi has been saved successfully");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
                response.setCode("500");
                response.setMessage("Divisi cannot have numbers");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("Divisi name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setData(null);
        response.setMessage("Divisi already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> updateDivisi(MstDivisiDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        MstDivisi divisis = divRepo.findByDivisiId(requestDTO.getDivisiId());

        if (Objects.equals(divisis.getDivisiId(), requestDTO.getDivisiId())) {
            MstDivisi divisi = new MstDivisi();
            if (isNullStr(requestDTO.getNamaDivisi())){
                if (validStrOnly(requestDTO.getNamaDivisi())){
                    divisi.setDivisiId(requestDTO.getDivisiId());
                    divisi.setNamaDivisi(requestDTO.getNamaDivisi());

                    divRepo.save(divisi);

                    response.setCode("201");
                    response.setData(null);
                    response.setMessage("Divisi has been saved successfully");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
                response.setCode("500");
                response.setMessage("Divisi cannot have numbers");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("Divisi name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setData(null);
        response.setMessage("Divisi already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
