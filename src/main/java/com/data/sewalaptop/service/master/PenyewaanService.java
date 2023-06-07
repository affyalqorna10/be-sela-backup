package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.MstPenyewaanDTO;
import com.data.sewalaptop.model.master.MstKaryawan;
import com.data.sewalaptop.model.master.MstPenyewaan;
import com.data.sewalaptop.model.master.MstUser;
import com.data.sewalaptop.model.master.Pengajuan;
import com.data.sewalaptop.repository.master.KaryawanRepository;
import com.data.sewalaptop.repository.master.PengajuanRepository;
import com.data.sewalaptop.repository.master.PenyewaanRepository;
import com.data.sewalaptop.repository.master.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenyewaanService {

    @Autowired
    private PenyewaanRepository penyewaanRepo;

    @Autowired
    private PengajuanRepository pengajuanRepo;

    @Autowired
    private KaryawanRepository karyawanRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PengajuanService pengajuanService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private UserService userService;

    public ResponseEntity<?> savePenyewaan(MstPenyewaanDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        Pengajuan pengajuan = pengajuanRepo.findByPengajuanId(requestDTO.getPengajuanId());
        MstKaryawan karyawan = karyawanRepo.findByKaryawanId(requestDTO.getKaryawanId());
        MstUser user = userRepo.findByUserId(requestDTO.getUserId());

        if (pengajuan.getPengajuanId() == null){
            response.setCode("204");
            response.setMessage("Pengajuan Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (karyawan.getKaryawanId() == null){
            response.setCode("204");
            response.setMessage("Karyawan Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (user.getUserId() == null){
            response.setCode("204");
            response.setMessage("User Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstPenyewaan penyewaanEntity = new MstPenyewaan();
        MstPenyewaan penyewaan = penyewaanRepo.findByPenyewaanId(requestDTO.getPenyewaanId());

        if (penyewaan == null){
            penyewaanEntity.setPengajuanId(requestDTO.getPengajuanId());
            penyewaanEntity.setTglPenyewaan(requestDTO.getTglPenyewaan());
            penyewaanEntity.setKaryawanId(requestDTO.getKaryawanId());
            penyewaanEntity.setTglMulai(requestDTO.getTglMulai());
            penyewaanEntity.setJatuhTempo(requestDTO.getJatuhTempo());
            penyewaanEntity.setUserId(requestDTO.getUserId());
            penyewaanEntity.setStatus(requestDTO.getStatus());

            penyewaanRepo.save(penyewaanEntity);
        }
        response.setCode("201");
        response.setData(null);
        response.setMessage("Penyewaan has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstPenyewaan> penyewaans =penyewaanRepo.findAll();

        response.setCode("200");
        response.setData(penyewaans);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
