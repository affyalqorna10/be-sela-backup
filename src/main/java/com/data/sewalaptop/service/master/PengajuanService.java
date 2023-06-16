package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.PengajuanDTO;
import com.data.sewalaptop.model.master.*;
import com.data.sewalaptop.repository.master.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.data.sewalaptop.common.Checker.isNullStr;
import static java.util.Objects.isNull;

@Service
public class PengajuanService {

    @Autowired
    private PengajuanRepository pengajuanRepo;

    @Autowired
    private SpesificationService spesificationService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private KaryawanRepository karyawanRepo;

    @Autowired
    private SpesificationRepository spesificationRepo;

    public ResponseEntity<?> savePengajuan(PengajuanDTO requestDTO){
        if (requestDTO.getPengajuanId() == null) {
            return createPengajuan(requestDTO);
        }
        return updatePengajuan(requestDTO);
    }

    private ResponseEntity<?> createPengajuan(PengajuanDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        MstSpesifikasi spesifikasi = spesificationRepo.findBySpekId(requestDTO.getSpekId());
        MstKaryawan karyawans = karyawanRepo.findByKaryawanId(requestDTO.getKaryawanId());

        if (spesifikasi.getSpekId() == null) {
            response.setCode("204");
            response.setMessage("Spesifikasi Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (karyawans.getKaryawanId() == null){
            response.setCode("204");
            response.setMessage("karyawam Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Pengajuan pengajuanEntity = new Pengajuan();
        Pengajuan pengajuan = pengajuanRepo.findByPengajuanId(requestDTO.getPengajuanId());

        if (pengajuan == null) {
            pengajuanEntity.setSpekId(requestDTO.getSpekId());
            pengajuanEntity.setKaryawanId(requestDTO.getKaryawanId());
            pengajuanEntity.setNoMemo(requestDTO.getNoMemo());
            pengajuanEntity.setTglPengajuan(requestDTO.getTglPengajuan());
            pengajuanEntity.setTglPenerima(requestDTO.getTglPenerima());
            pengajuanEntity.setStatus(requestDTO.getStatus());

            pengajuanRepo.save(pengajuanEntity);
        }
        response.setCode("201");
        response.setData(null);
        response.setMessage("Pengajuan has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> updatePengajuan(PengajuanDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        Pengajuan pengajuanEntity = new Pengajuan();
        Pengajuan pengajuanList = pengajuanRepo.findByPengajuanId(requestDTO.getPengajuanId());

        if (pengajuanList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Pengajuan pengajuan = pengajuanRepo.findByPengajuanId(requestDTO.getPengajuanId());
        if (requestDTO.getPengajuanId() == null){
            pengajuanEntity.setPengajuanId(pengajuan.getPengajuanId());
        }else{
            pengajuanEntity.setPengajuanId(requestDTO.getPengajuanId());
        }

        if (requestDTO.getSpekId() == null){
            pengajuanEntity.setSpekId(pengajuan.getSpekId());
        }else{
            pengajuanEntity.setSpekId(requestDTO.getSpekId());
        }

        if (requestDTO.getKaryawanId() == null){
            pengajuanEntity.setKaryawanId(pengajuan.getKaryawanId());
        }else {
            pengajuanEntity.setKaryawanId(requestDTO.getKaryawanId());
        }

        if (isNullStr(requestDTO.getNoMemo())){
            pengajuanEntity.setNoMemo(requestDTO.getNoMemo());
        }else{
            pengajuanEntity.setNoMemo(requestDTO.getNoMemo());
        }

        if (isNull(requestDTO.getTglPengajuan())){
            pengajuanEntity.setTglPengajuan(requestDTO.getTglPengajuan());
        }else{
            pengajuanEntity.setTglPengajuan(requestDTO.getTglPengajuan());
        }

        if (isNull(requestDTO.getTglPenerima())){
            pengajuanEntity.setTglPenerima(requestDTO.getTglPenerima());
        }else{
            pengajuanEntity.setTglPenerima(requestDTO.getTglPenerima());
        }

        if (isNullStr(requestDTO.getStatus())){
            pengajuanEntity.setStatus(requestDTO.getStatus());
        }else{
            pengajuanEntity.setStatus(requestDTO.getStatus());
        }

        pengajuanRepo.save(pengajuanEntity);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Pengajuan has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<Pengajuan> pengajuan1 = pengajuanRepo.findAll();

        response.setCode("200");
        response.setData(pengajuan1);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByPengajuanId(Long pengajuanId){
        ResponseDTO response = new ResponseDTO();
        Pengajuan pengajuan = pengajuanRepo.findByPengajuanId(pengajuanId);

        response.setCode("200");
        response.setData(pengajuan);
        response.setMessage("Get Data by pengajuan id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> deletePengajuan(Long pengajuanId){
        ResponseDTO response = new ResponseDTO();
        Pengajuan pengajuan = pengajuanRepo.findByPengajuanId(pengajuanId);
        if (pengajuan == null){
            response.setCode("204");
            response.setMessage("Pengajuan ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        pengajuanRepo.delete(pengajuan);
        response.setCode("200");
        response.setData(null);
        response.setMessage("Pengajuan Id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
