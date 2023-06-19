package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.MstVendorDTO;
import com.data.sewalaptop.model.master.MstVendor;
import com.data.sewalaptop.repository.master.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.data.sewalaptop.common.Checker.isNullStr;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepo;

    public ResponseEntity<?> saveVendor(MstVendorDTO requestDTO){
        if (requestDTO.getVendorId() == null){
            return createVendor(requestDTO);
        }
        return updateVendor(requestDTO);
    }

    public ResponseEntity<?> createVendor(MstVendorDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstVendor vendorEntity = new MstVendor();
        MstVendor vendor = vendorRepo.findByVendorId(requestDTO.getVendorId());
        if(vendor == null){
            if (isNullStr(requestDTO.getNamaVendor())){
                if (isNullStr(requestDTO.getEmail())){
                    if (isNullStr(requestDTO.getAlamatVendor())){
                        if (isNullStr(requestDTO.getTelpVendor())){
                            vendorEntity.setNamaVendor(requestDTO.getNamaVendor());
                            vendorEntity.setEmail(requestDTO.getEmail());
                            vendorEntity.setAlamatVendor(requestDTO.getAlamatVendor());
                            vendorEntity.setTelpVendor(requestDTO.getTelpVendor());

                            vendorRepo.save(vendorEntity);

                            response.setCode("201");
                            response.setData(null);
                            response.setMessage("Vendor has been saved successfully");
                            return new ResponseEntity<>(response, HttpStatus.CREATED);
                        }
                        response.setCode("204");
                        response.setMessage("Telp Vendor cannot be empty");
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    response.setCode("204");
                    response.setMessage("Telp Vendor cannot be empty");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                response.setCode("204");
                response.setMessage("Email Vendor cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("Name Vendor cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setMessage("Data Spesification already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> updateVendor(MstVendorDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstVendor vendorEntity = new MstVendor();
        MstVendor vendorList = vendorRepo.findByVendorId(requestDTO.getVendorId());

        if (vendorList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstVendor vendor = vendorRepo.findByVendorId(requestDTO.getVendorId());
        if (requestDTO.getVendorId() == null){
            vendorEntity.setVendorId(vendor.getVendorId());
        } else {
            vendorEntity.setVendorId(requestDTO.getVendorId());
        }
        if (isNullStr(requestDTO.getNamaVendor())){
            vendorEntity.setNamaVendor(requestDTO.getNamaVendor());
        }else {
            vendorEntity.setNamaVendor(vendor.getNamaVendor());
        }
        if (isNullStr(requestDTO.getEmail())){
            vendorEntity.setEmail(requestDTO.getEmail());
        }else {
            vendorEntity.setEmail(requestDTO.getEmail());
        }
        if (isNullStr(requestDTO.getAlamatVendor())){
            vendorEntity.setAlamatVendor(requestDTO.getAlamatVendor());
        }else {
            vendorEntity.setAlamatVendor(requestDTO.getAlamatVendor());
        }
        if (isNullStr(requestDTO.getTelpVendor())){
            vendorEntity.setTelpVendor(requestDTO.getTelpVendor());
        }else {
            vendorEntity.setTelpVendor(requestDTO.getTelpVendor());
        }
        vendorRepo.save(vendorEntity);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Karyawan has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstVendor> vendorList = vendorRepo.findAll();

        response.setCode("200");
        response.setData(vendorList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteVendor(Long vendorId){
        ResponseDTO response = new ResponseDTO();
        MstVendor vendor = vendorRepo.findByVendorId(vendorId);
        if (vendor == null){
            response.setCode("204");
            response.setMessage("Vendor ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        vendorRepo.delete(vendor);
        response.setCode("200");
        response.setData(null);
        response.setMessage("Vendor Id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByVendorId(Long vendorId){
        ResponseDTO response = new ResponseDTO();
        MstVendor vendor = vendorRepo.findByVendorId(vendorId);
        if (vendor == null){
            response.setCode("204");
            response.setMessage("Vendor ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(vendor);
        response.setMessage("Get Data By Vendor Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByNamaVendor(String namaVendor){
        ResponseDTO response = new ResponseDTO();
        MstVendor vendor = vendorRepo.findByNamaVendor(namaVendor);

        response.setCode("200");
        response.setData(vendor);
        response.setMessage("Get Data By Nama Vendor successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
