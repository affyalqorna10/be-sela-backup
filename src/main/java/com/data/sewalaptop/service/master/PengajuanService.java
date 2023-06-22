package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.dto.transaction.PengajuanResponse;
import com.data.sewalaptop.model.master.*;
import com.data.sewalaptop.repository.master.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.data.sewalaptop.common.Checker.isNullStr;
import static java.util.Objects.isNull;

@Service
@Slf4j
public class PengajuanService {

    @Autowired
    private PengajuanRepository pengajuanRepo;

    @Autowired
    private SpesificationRepository spesificationRepo;

    @Autowired
    private KaryawanRepository karyawanRepo;

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private DivisiRespository divisiRespo;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SpesificationService spesificationService;

    @Autowired
    private KaryawanService karyawanService;

    public ResponseEntity<?> savePengajuan(MstPengajuanDTO requestDTO){
        if (requestDTO.getPengajuanId() == null) {
            return createPengajuan(requestDTO);
        }
        return updatePengajuan(requestDTO);
    }

    private ResponseEntity<?> createPengajuan(MstPengajuanDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstSpesifikasi spesifikasi = spesificationRepo.findBySpekId(requestDTO.getSpekId());
        MstKaryawan karyawan = karyawanRepo.findByKaryawanId(requestDTO.getKaryawanId());

        if (spesifikasi.getSpekId() == null){
            response.setCode("204");
            response.setMessage("Spesifikasi Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (karyawan.getKaryawanId() == null){
            response.setCode("204");
            response.setMessage("karyawam Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        MstPengajuan pengajuanEntity = new MstPengajuan();
        MstPengajuan pengajuan = pengajuanRepo.findByPengajuanId(requestDTO.getPengajuanId());

        if (pengajuan == null){
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

    private ResponseEntity<?> updatePengajuan(MstPengajuanDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstPengajuan pengajuanEntity = new MstPengajuan();
        MstPengajuan pengajuanList = pengajuanRepo.findByPengajuanId(requestDTO.getPengajuanId());

        if (pengajuanList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        MstPengajuan pengajuan = pengajuanRepo.findByPengajuanId(requestDTO.getPengajuanId());
        if (requestDTO.getPengajuanId() == null){
            pengajuanEntity.setPengajuanId(pengajuan.getPengajuanId());
        }else {
            pengajuanEntity.setPengajuanId(requestDTO.getPengajuanId());
        }

        if (requestDTO.getSpekId() == null){
            pengajuanEntity.setSpekId(pengajuan.getSpekId());
        }else{
            pengajuanEntity.setSpekId(requestDTO.getSpekId());
        }

        if (requestDTO.getKaryawanId() == null){
            pengajuanEntity.setKaryawanId(pengajuan.getKaryawanId());
        }else{
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
        List<MstPengajuan> pengajuans = pengajuanRepo.findAll();
        List<PengajuanResponse> responses = new ArrayList<>();

        for(MstPengajuan pengajuan:pengajuans){
            PengajuanResponse resp = new PengajuanResponse();
            resp.setPengajuanId(pengajuan.getPengajuanId());
            resp.setSpekId(pengajuan.getSpekId());
            resp.setKaryawanId(pengajuan.getKaryawanId());
            resp.setNoMemo(pengajuan.getNoMemo());
            resp.setTglPengajuan(pengajuan.getTglPengajuan());
            resp.setTglPenerima(pengajuan.getTglPenerima());
            resp.setStatus(pengajuan.getStatus());

            MstKaryawan karyawan = karyawanRepo.findByKaryawanId(resp.getKaryawanId());
            MstKaryawanDTO dtoKaryawan = new MstKaryawanDTO();

            dtoKaryawan.setKaryawanId(karyawan.getKaryawanId());
            dtoKaryawan.setDivisiId(karyawan.getDivisiId());
            dtoKaryawan.setNikKaryawan(karyawan.getNikKaryawan());
            dtoKaryawan.setNamaDepan(karyawan.getNamaDepan());
            dtoKaryawan.setNamaBelakang(karyawan.getNamaBelakang());

            MstDivisi divisi = divisiRespo.findByDivisiId(karyawan.getDivisiId());
            MstDivisiDTO dtoDivisi = new MstDivisiDTO();

            dtoDivisi.setDivisiId(divisi.getDivisiId());
            dtoDivisi.setNamaDivisi(divisi.getNamaDivisi());

            MstSpesifikasi spesifikasi = spesificationRepo.findBySpekId(resp.getSpekId());
            MstSpesifikasiDTO dtoSpesifikasi = new MstSpesifikasiDTO();

            dtoSpesifikasi.setStorage(spesifikasi.getStorage());
            dtoSpesifikasi.setProcessor(spesifikasi.getProcessor());
            dtoSpesifikasi.setRam(spesifikasi.getRam());
            dtoSpesifikasi.setGraphicCard(spesifikasi.getGraphicCard());

            MstDevices devices = deviceRepo.findByDeviceId(spesifikasi.getDeviceId());
            MstDevicesDTO dtoDevice = new MstDevicesDTO();

            dtoDevice.setDeviceName(devices.getDeviceName());

            resp.setSpesifikasi(dtoSpesifikasi);
            resp.setKaryawan(dtoKaryawan);
            resp.setDivisi(dtoDivisi);
            resp.setDevice(dtoDevice);

            responses.add(resp);
        }

        response.setCode("200");
        response.setData(responses);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByAllPengajuanId(Long pengajuanId){
        ResponseDTO response = new ResponseDTO();
        MstPengajuan pengajuan = pengajuanRepo.findByPengajuanId(pengajuanId);
        PengajuanResponse resp = new PengajuanResponse();
        if(pengajuan==null){
            response.setCode("400");
            response.setMessage("Err:Pengajuan tidak ditemukan");
            response.setData(null);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        resp.setPengajuanId(pengajuan.getPengajuanId());
        resp.setSpekId(pengajuan.getSpekId());
        resp.setKaryawanId(pengajuan.getKaryawanId());
        resp.setNoMemo(pengajuan.getNoMemo());
        resp.setTglPengajuan(pengajuan.getTglPengajuan());
        resp.setTglPenerima(pengajuan.getTglPenerima());
        resp.setStatus(pengajuan.getStatus());

        MstKaryawan karyawan = karyawanRepo.findByKaryawanId(resp.getKaryawanId());
        MstKaryawanDTO dtoKaryawan = new MstKaryawanDTO();

        dtoKaryawan.setKaryawanId(karyawan.getKaryawanId());
        dtoKaryawan.setDivisiId(karyawan.getDivisiId());
        dtoKaryawan.setNikKaryawan(karyawan.getNikKaryawan());
        dtoKaryawan.setNamaDepan(karyawan.getNamaDepan());
        dtoKaryawan.setNamaBelakang(karyawan.getNamaBelakang());

        MstDivisi divisi = divisiRespo.findByDivisiId(karyawan.getDivisiId());
        MstDivisiDTO dtoDivisi = new MstDivisiDTO();

        dtoDivisi.setDivisiId(divisi.getDivisiId());
        dtoDivisi.setNamaDivisi(divisi.getNamaDivisi());

        MstSpesifikasi spesifikasi = spesificationRepo.findBySpekId(resp.getSpekId());
        MstSpesifikasiDTO dtoSpesifikasi = new MstSpesifikasiDTO();

        dtoSpesifikasi.setStorage(spesifikasi.getStorage());
        dtoSpesifikasi.setProcessor(spesifikasi.getProcessor());
        dtoSpesifikasi.setRam(spesifikasi.getRam());
        dtoSpesifikasi.setGraphicCard(spesifikasi.getGraphicCard());

        MstDevices devices = deviceRepo.findByDeviceId(spesifikasi.getDeviceId());
        MstDevicesDTO dtoDevice = new MstDevicesDTO();

        dtoDevice.setDeviceName(devices.getDeviceName());

        resp.setSpesifikasi(dtoSpesifikasi);
        resp.setKaryawan(dtoKaryawan);
        resp.setDivisi(dtoDivisi);
        resp.setDevice(dtoDevice);
        if (pengajuan == null){
            response.setCode("204");
            response.setMessage("All Pengajuan ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("200");
        response.setData(resp);
        response.setMessage("Get Data By Karyawan Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> deletePengajuan(Long pengajuanId){
        ResponseDTO response = new ResponseDTO();
        MstPengajuan pengajuan = pengajuanRepo.findByPengajuanId(pengajuanId);

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
