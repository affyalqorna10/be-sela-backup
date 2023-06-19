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

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private DeviceService deviceService;

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
            response.setMessage("Spesification ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        spekRepo.delete(spek);

        response.setCode("200");
        response.setData(null);
        response.setMessage("Spesification id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByDeviceId(Long deviceId){
        ResponseDTO response = new ResponseDTO();
        MstSpesifikasi device = spekRepo.findByDeviceId(deviceId);
        if (device == null){
            response.setCode("204");
            response.setMessage("Spek ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(device);
        response.setMessage("Get Data by Device id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getBySpekId(Long spekId){
        ResponseDTO response = new ResponseDTO();
        MstSpesifikasi spek = spekRepo.findBySpekId(spekId);
        if (spek == null){
            response.setCode("204");
            response.setMessage("Spek ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(spek);
        response.setMessage("Get Data by Spek Id successfully");
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
        MstSpesifikasi spek = spekRepo.findByDeviceId(requestDTO.getDeviceId());
        if (spek == null) {
            if (isNullStr(requestDTO.getDeviceName())){
            if (isNullStr(requestDTO.getStorage())){
                if (isNullStr(requestDTO.getProcessor())){
                    if (isNullStr(requestDTO.getRam())){
                        if (isNullStr(requestDTO.getGraphicCard())) {

                            spekEntity.setDeviceId(requestDTO.getDeviceId());
                            spekEntity.setDeviceName(requestDTO.getDeviceName());
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
                        response.setMessage("Device Name cannot be empty");
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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

        if (requestDTO.getDeviceId() == null) {
            spekEntity.setDeviceId(spek.getDeviceId());
        }else{
            spekEntity.setDeviceId(requestDTO.getDeviceId());
        }

        if (isNullStr(requestDTO.getDeviceName())){
            spekEntity.setDeviceName(spek.getDeviceName());
        }else{
            spekEntity.setDeviceName(requestDTO.getDeviceName());
        }

        if (isNullStr(requestDTO.getStorage())){
            spekEntity.setStorage(requestDTO.getStorage());
        }else{
            spekEntity.setStorage(requestDTO.getStorage());
        }

        if (isNullStr(requestDTO.getProcessor())){
            spekEntity.setProcessor(requestDTO.getProcessor());
        }else{
            spekEntity.setProcessor(requestDTO.getProcessor());
        }

        if (isNullStr(requestDTO.getRam())){
            spekEntity.setRam(requestDTO.getRam());
        }else{
            spekEntity.setRam(requestDTO.getRam());
        }

        if (isNullStr(requestDTO.getGraphicCard())){
            spekEntity.setGraphicCard(requestDTO.getGraphicCard());
        }else{
            spekEntity.setGraphicCard(requestDTO.getGraphicCard());
        }

        spekRepo.save(spekEntity);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Spesification has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
