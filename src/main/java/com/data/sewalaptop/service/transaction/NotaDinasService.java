package com.data.sewalaptop.service.transaction;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.transaction.MstNotaDinasDTO;
import com.data.sewalaptop.model.master.MstKaryawan;
import com.data.sewalaptop.model.transaction.MstNotaDinas;
import com.data.sewalaptop.repository.master.KaryawanRepository;
import com.data.sewalaptop.repository.transaction.NotaDinasRepository;
import com.data.sewalaptop.service.master.KaryawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaDinasService {
    @Autowired
    private NotaDinasRepository nodinRepo;

    @Autowired
    private KaryawanRepository karyawanRepo;

    @Autowired
    private KaryawanService karyawanService;

    public ResponseEntity<?> saveNotaDinas(MstNotaDinasDTO requestDTO){
        if (requestDTO.getNodinId() == null) {
            return createNotaDinas(requestDTO);
        }
        return updateNotaDinas(requestDTO);
    }

    private ResponseEntity<?> createNotaDinas(MstNotaDinasDTO requestDTO){
        ResponseDTO response = new ResponseDTO();

        //save Nota Dinas
        MstKaryawan karyawan = karyawanRepo.findByNikKaryawan(requestDTO.getNIK());
        if (karyawan == null){
            response.setCode("204");
            response.setData(null);
            response.setMessage("karyawan Not Found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstNotaDinas nodinDTO = new MstNotaDinas();
        nodinDTO.setKaryawanId(requestDTO.getKaryawanId());
        nodinDTO.setNIK(requestDTO.getNIK());
        nodinDTO.setNotaDinas(requestDTO.getNotaDinas());
        nodinDTO.setTglMulai(requestDTO.getTglMulai());
        nodinDTO.setJatuhTempo(requestDTO.getJatuhTempo());

        nodinRepo.save(nodinDTO);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Nota Dinas has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> updateNotaDinas(MstNotaDinasDTO requestDTO){
        ResponseDTO response = new ResponseDTO();

        MstNotaDinas nodin = nodinRepo.findByNodinId(requestDTO.getNodinId());
        if (nodin == null){
            response.setCode("204");
            response.setData(null);
            response.setMessage("Nodin ID Not Found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstKaryawan karyawan = karyawanRepo.findByNikKaryawan(requestDTO.getNIK());
        if (karyawan == null){
            response.setCode("204");
            response.setData(null);
            response.setMessage("karyawan NIK Not Found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        nodin.setNodinId(requestDTO.getNodinId());
        nodin.setKaryawanId(requestDTO.getKaryawanId());
        nodin.setNIK(requestDTO.getNIK());
        nodin.setNotaDinas(requestDTO.getNotaDinas());
        nodin.setTglMulai(requestDTO.getTglMulai());
        nodin.setJatuhTempo(requestDTO.getJatuhTempo());

        nodinRepo.save(nodin);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Nota Dinas has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteNodin(Long nodinId){
        ResponseDTO response = new ResponseDTO();
        MstNotaDinas notaDinas = nodinRepo.findByNodinId(nodinId);
        if (notaDinas == null){
            response.setCode("204");
            response.setMessage("Nota Dinas ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("200");
        response.setData(null);
        response.setMessage("Nota Dinas ID successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByNodinId(Long nodinId){
        ResponseDTO response = new ResponseDTO();
        MstNotaDinas notaDinas = nodinRepo.findByNodinId(nodinId);

        response.setCode("200");
        response.setData(notaDinas);
        response.setMessage("Get Data By Brand Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstNotaDinas> notaDinas = nodinRepo.findAll();

        response.setCode("200");
        response.setData(notaDinas);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}

