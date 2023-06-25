package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.model.master.MstDevices;
import com.data.sewalaptop.repository.master.DeviceRepository;
import com.data.sewalaptop.service.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceRepository deviceRepository;

    //JwtService
    @Autowired JwtService jwtService;
//    private Object mstDevices;

    @PostMapping("/save")
    public ResponseEntity<?> saveDevice(@RequestHeader Map<String,String> header, @RequestBody MstDevicesDTO request){

        jwtService.filter(header);
        return deviceService.saveDevice(request);
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

    //Update
    @PutMapping("/update")
    public Map<String, Object> updateDevice (@RequestHeader Map<String,String> header, @RequestParam(value = "deviceId")
                                                 Long deviceId, @RequestBody MstDevicesDTO mstDevicesDto){
        Map<String, Object> mapResult = new HashMap<>();

        MstDevices mstDevices = deviceRepository.findById(deviceId).orElse(null);

        mstDevices.setDeviceName(mstDevicesDto.getDeviceName());

        mapResult.put("message", "Update success");
        mapResult.put("data", deviceRepository.save(mstDevices));

        jwtService.filter(header);
        return mapResult;
    }

    @DeleteMapping("/delete/{deviceId}")
    public ResponseEntity<?> deleteDevice(@RequestHeader Map<String,String> header, @PathVariable Long deviceId){

        jwtService.filter(header);
        return deviceService.deleteDevice(deviceId);
    }


}
