package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.*;
import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.model.master.*;
import com.data.sewalaptop.repository.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Objects;

import static com.data.sewalaptop.common.Checker.*;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepo;

    public ResponseEntity<?> saveDevice(MstDevicesDTO requestDTO) {
        if (requestDTO.getDeviceId() == null) {
            return createDevice(requestDTO);
        }
        return updateDevice(requestDTO);
    }

    public ResponseEntity<?> deleteDevice(Long deviceId){
        ResponseDTO response = new ResponseDTO();
        MstDevices devices = deviceRepo.findByDeviceId(deviceId);
        if (devices == null){
            response.setCode("204");
            response.setMessage("Device ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        deviceRepo.delete(devices);

        response.setCode("200");
        response.setData(null);
        response.setMessage("Device id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByDeviceId(Long deviceId){
        ResponseDTO response = new ResponseDTO();
        MstDevices devices = deviceRepo.findByDeviceId(deviceId);
        if (devices == null){
            response.setCode("204");
            response.setMessage("Device ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(devices);
        response.setMessage("Get Data By Device Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllDevice(){
        ResponseDTO response = new ResponseDTO();
        List<MstDevices> devicesList = deviceRepo.findAll();

        response.setCode("200");
        response.setData(devicesList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createDevice(MstDevicesDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstDevices devices = deviceRepo.findByDeviceId(requestDTO.getDeviceId());
        if (devices == null) {
            MstDevices device = new MstDevices();
            // Validate Brand Name null && string only
            if (isNullStr(requestDTO.getDeviceName())) {

                device.setDeviceName(requestDTO.getDeviceName().toUpperCase());
                deviceRepo.save(device);

                response.setCode("201");
                response.setData(null);
                response.setMessage("Device has been saved successfully");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            response.setCode("204");
            response.setMessage("Device name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setData(null);
        response.setMessage("Device already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> updateDevice(MstDevicesDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstDevices devices = deviceRepo.findByDeviceId(requestDTO.getDeviceId());

        if (Objects.equals(devices.getDeviceId(), requestDTO.getDeviceId())){
            MstDevices device = new MstDevices();
            if (isNullStr(requestDTO.getDeviceName())){
                device.setDeviceId(requestDTO.getDeviceId());
                device.setDeviceName(requestDTO.getDeviceName());

                deviceRepo.save(device);

                response.setCode("201");
                response.setData(null);
                response.setMessage("Device has been saved successfully");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            response.setCode("204");
            response.setMessage("Device name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setData(null);
        response.setMessage("Device already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
