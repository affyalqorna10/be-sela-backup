package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.service.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/spesification")
public class SpesificationController {
    @Autowired
    private SpesificationService spekService;

    @Autowired JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveSpesification(@RequestHeader Map<String,String> header, @RequestBody MstSpesifikasiDTO request){

        jwtService.filter(header);
        return spekService.saveSpesification(request);
    }

    @DeleteMapping("/delete/{idSpek}")
    public ResponseEntity<?> deleteSpek(@RequestHeader Map<String,String> header, @PathVariable Long idSpek){

        jwtService.filter(header);
        return spekService.deleteSpesification(idSpek);
    }

    @GetMapping("/get_device_by/{deviceId}")
    public ResponseEntity<?> getByDeviceId(@RequestHeader Map<String,String> header, @PathVariable Long deviceId){

        jwtService.filter(header);
        return spekService.getByDeviceId(deviceId);
    }

    @GetMapping("/get_spek_by/{spekId}")
    public ResponseEntity<?> getBySpekId(@RequestHeader Map<String,String> header, @PathVariable Long spekId){

        jwtService.filter(header);
        return spekService.getBySpekId(spekId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return spekService.getAll();
    }
}
