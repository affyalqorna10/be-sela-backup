package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.MstVendorDTO;
import com.data.sewalaptop.dto.master.PengajuanDTO;
import com.data.sewalaptop.dto.transaction.MstNotaDinasDTO;
import com.data.sewalaptop.model.master.*;
import com.data.sewalaptop.model.transaction.MstNotaDinas;
import com.data.sewalaptop.repository.master.*;
import com.data.sewalaptop.repository.transaction.NotaDinasRepository;
import com.data.sewalaptop.service.transaction.NotaDinasService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.data.sewalaptop.common.Checker.isNullStr;
import static java.util.Objects.isNull;

@Service
public class PengajuanService {

    @Autowired
    private PengajuanRepository pengajuanRepo;

    @Autowired
    private NotaDinasService notaDinasService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private UserService userService;

    @Autowired
    private DivisiService divisiService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private NotaDinasRepository notaDinasRepo;

    @Autowired
    private VendorRepository vendorRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private KaryawanRepository karyawanRepo;

    @Autowired
    private DivisiRespository divisiRespo;

    public ResponseEntity<?> savePengajuan(PengajuanDTO requestDTO){
        if (requestDTO.getPengajuanId() == null) {
            return createPengajuan(requestDTO);
        }
        return updatePengajuan(requestDTO);
    }

    private ResponseEntity<?> createPengajuan(PengajuanDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        MstUser user = userRepo.findByUserId(requestDTO.getUserId());
        MstKaryawan karyawan = karyawanRepo.findByNikKaryawan(requestDTO.getNikKaryawan());
        MstKaryawan karyawan1 = karyawanRepo.findByKaryawanId(requestDTO.getKaryawanId());
        MstDivisi divisi = divisiRespo.findByDivisiId(requestDTO.getDivisiId());

        if (user.getUserId() == null) {
            response.setCode("204");
            response.setMessage("User Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (karyawan.getNikKaryawan() == null) {
            response.setCode("204");
            response.setMessage("karyawam Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (divisi.getDivisiId() == null){
            response.setCode("204");
            response.setMessage("Divisi Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (karyawan1.getKaryawanId() == null){
            response.setCode("204");
            response.setMessage("karyawam Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Pengajuan pengajuanEntity = new Pengajuan();
        Pengajuan pengajuan = pengajuanRepo.findByPengajuanId(requestDTO.getPengajuanId());

        if (pengajuan == null) {
            pengajuanEntity.setUserId(requestDTO.getUserId());
            pengajuanEntity.setKaryawanId(requestDTO.getKaryawanId());
            pengajuanEntity.setNikKaryawan(requestDTO.getNikKaryawan());
            pengajuanEntity.setDivisiId(requestDTO.getDivisiId());
            pengajuanEntity.setNoMemo(requestDTO.getNoMemo());
            pengajuanEntity.setTglPengajuan(requestDTO.getTglPengajuan());
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

        if (requestDTO.getUserId() == null){
            pengajuanEntity.setUserId(pengajuan.getUserId());
        }else{
            pengajuanEntity.setUserId(requestDTO.getUserId());
        }

        if (requestDTO.getKaryawanId() == null){
            pengajuanEntity.setKaryawanId(pengajuan.getKaryawanId());
        }else {
            pengajuanEntity.setKaryawanId(requestDTO.getKaryawanId());
        }

        if (requestDTO.getDivisiId() == null){
            pengajuanEntity.setDivisiId(pengajuan.getDivisiId());
        }else {
            pengajuanEntity.setDivisiId(requestDTO.getDivisiId());
        }

        if (requestDTO.getNikKaryawan() == null){
            pengajuanEntity.setNikKaryawan(pengajuan.getNikKaryawan());
        }else{
            pengajuanEntity.setNikKaryawan(requestDTO.getNikKaryawan());
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

    public ResponseEntity<?> getByNoMemo(String noMemo){
        ResponseDTO response = new ResponseDTO();
        Pengajuan pengajuan = pengajuanRepo.findByNoMemo(noMemo);

        response.setCode("200");
        response.setData(pengajuan);
        response.setMessage("Get Data by Memo successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
