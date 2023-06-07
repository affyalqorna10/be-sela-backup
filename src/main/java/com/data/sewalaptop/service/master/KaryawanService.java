package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.MstKaryawanDTO;
import com.data.sewalaptop.model.master.MstKaryawan;
import com.data.sewalaptop.repository.master.KaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.data.sewalaptop.common.Checker.isNullStr;

@Service
public class  KaryawanService {
    @Autowired
    private KaryawanRepository karyawanRepo;

    public ResponseEntity<?> savekaryawan(MstKaryawanDTO requestDTO){
        if (requestDTO.getKaryawanId() == null) {
            return createKaryawan(requestDTO);
        }
        return updateKaryawan(requestDTO);
    }

    public ResponseEntity<?> deleteKaryawan(Long karyawanId){
        ResponseDTO response = new ResponseDTO();
        MstKaryawan karyawan = karyawanRepo.findByKaryawanId(karyawanId);
        if (karyawan == null){
            response.setCode("204");
            response.setMessage("Karyawan ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        karyawanRepo.delete(karyawan);

        response.setCode("200");
        response.setData(null);
        response.setMessage("Karyawan Id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByNIK(String nikKaryawan){
        ResponseDTO response = new ResponseDTO();
        MstKaryawan karyawan = karyawanRepo.findByNikKaryawan(nikKaryawan);

        response.setCode("200");
        response.setData(karyawan);
        response.setMessage("Get Data By Karyawan NIK successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getBykaryawanId(Long karyawanId){
        ResponseDTO response = new ResponseDTO();
        MstKaryawan karyawan = karyawanRepo.findByKaryawanId(karyawanId);

        response.setCode("200");
        response.setData(karyawan);
        response.setMessage("Get Data By Karyawan Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByDivisiId(Long divisiId){
        ResponseDTO response = new ResponseDTO();
        List<MstKaryawan> karyawanList = karyawanRepo.findAllByDivisiId(divisiId);
        if (karyawanList == null){
            response.setCode("204");
            response.setMessage("Divisi ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("200");
        response.setData(karyawanList);
        response.setMessage("Get All Data By Divisi Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstKaryawan> karyawanList = karyawanRepo.findAll();

        response.setCode("200");
        response.setData(karyawanList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createKaryawan(MstKaryawanDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstKaryawan karyawanEntity = new MstKaryawan();
        MstKaryawan karyawan = karyawanRepo.findByKaryawanId(requestDTO.getKaryawanId());
        if (karyawan == null) {
            if (isNullStr(requestDTO.getNamaDepan())) {
                if (isNullStr(requestDTO.getNamaBelakang()))
                    if (isNullStr(requestDTO.getNikKaryawan())) {
                        if (isNullStr(requestDTO.getEmailKaryawan())) {
                            if (isNullStr(requestDTO.getAlamatKaryawan())) {
                                if (isNullStr(requestDTO.getTelpKaryawan())) {
                                    karyawanEntity.setDivisiId(requestDTO.getDivisiId());
                                    karyawanEntity.setNamaDepan(requestDTO.getNamaDepan());
                                    karyawanEntity.setNamaBelakang(requestDTO.getNamaBelakang());
                                    karyawanEntity.setNikKaryawan(requestDTO.getNikKaryawan());
                                    karyawanEntity.setEmailKaryawan(requestDTO.getEmailKaryawan());
                                    karyawanEntity.setAlamatKaryawan(requestDTO.getAlamatKaryawan());
                                    karyawanEntity.setTelpKaryawan(requestDTO.getTelpKaryawan());

                                    karyawanRepo.save(karyawanEntity);

                                    response.setCode("201");
                                    response.setData(null);
                                    response.setMessage("Karyawan has been saved successfully");
                                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                            }
                            response.setCode("204");
                            response.setMessage("Telp Karyawan cannot be empty");
                            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                        response.setCode("204");
                        response.setMessage("Alamat Karyawan cannot be empty");
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    response.setCode("204");
                    response.setMessage("Email Karyawan cannot be empty");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                response.setCode("204");
                response.setMessage("NIK Karyawan cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("Nama Karyawan cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setMessage("Data Spesification already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> updateKaryawan(MstKaryawanDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstKaryawan karyawanEntity = new MstKaryawan();
        MstKaryawan karyawanList = karyawanRepo.findByKaryawanId(requestDTO.getKaryawanId());

        if (karyawanList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstKaryawan karyawan = karyawanRepo.findByKaryawanId(requestDTO.getKaryawanId());
        if (requestDTO.getKaryawanId() == null) {
            karyawanEntity.setKaryawanId(karyawan.getKaryawanId());
        } else {
            karyawanEntity.setKaryawanId(requestDTO.getKaryawanId());
        }
        if (requestDTO.getDivisiId() == null){
            karyawanEntity.setDivisiId(karyawan.getDivisiId());
        }else{
            karyawanEntity.setDivisiId(requestDTO.getDivisiId());
        }
        if (isNullStr(requestDTO.getNamaDepan())){
            karyawanEntity.setNamaDepan(requestDTO.getNamaDepan());
        } else {
            karyawanEntity.setNamaDepan(karyawan.getNamaDepan());
        }
        if (isNullStr(requestDTO.getNamaBelakang())){
            karyawanEntity.setNamaBelakang(requestDTO.getNamaBelakang());
        } else {
            karyawanEntity.setNamaBelakang(karyawan.getNamaBelakang());
        }
        if (isNullStr(requestDTO.getNikKaryawan())) {
            karyawanEntity.setNikKaryawan(requestDTO.getNikKaryawan());
        } else {
            karyawanEntity.setNikKaryawan(karyawan.getNikKaryawan());
        }
        if (isNullStr(requestDTO.getEmailKaryawan())){
            karyawanEntity.setEmailKaryawan(requestDTO.getEmailKaryawan());
        } else {
            karyawanEntity.setEmailKaryawan(karyawan.getEmailKaryawan());
        }
        if (isNullStr(requestDTO.getAlamatKaryawan())){
            karyawanEntity.setAlamatKaryawan(requestDTO.getAlamatKaryawan());
        } else {
            karyawanEntity.setAlamatKaryawan(karyawan.getAlamatKaryawan());
        }
        if (isNullStr(requestDTO.getTelpKaryawan())){
            karyawanEntity.setTelpKaryawan(requestDTO.getTelpKaryawan());
        }else{
            karyawanEntity.setTelpKaryawan(karyawan.getTelpKaryawan());
        }
        karyawanRepo.save(karyawanEntity);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Karyawan has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
