package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.service.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    //JwtService
    @Autowired JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveDevice(@RequestHeader Map<String,String> header, @RequestBody MstDevicesDTO request){

        jwtService.filter(header);
        return deviceService.saveDevice(request);
    }

    @DeleteMapping("/delete/{deviceId}")
    public ResponseEntity<?> deleteDevice(@RequestHeader Map<String,String> header, @PathVariable Long deviceId){

        jwtService.filter(header);
        return deviceService.deleteDevice(deviceId);
    }

    @GetMapping("/get_by/{deviceId}")
    public ResponseEntity<?> getByDeviceId(@RequestHeader Map<String,String> header,@PathVariable Long deviceId){

        jwtService.filter(header);
        return deviceService.getByDeviceId(deviceId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllDevice(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return deviceService.getAllDevice();
    }
}
