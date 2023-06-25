package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.model.master.MstDevices;
import com.data.sewalaptop.model.master.MstSpesifikasi;
import com.data.sewalaptop.repository.master.SpesificationRepository;
import com.data.sewalaptop.service.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/spesification")
public class SpesificationController {
    @Autowired
    private SpesificationService spekService;

    @Autowired
    private SpesificationRepository spesificationRepository;

    @Autowired JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveSpesification(@RequestHeader Map<String,String> header,
                                               @RequestBody MstSpesifikasiDTO request){

        jwtService.filter(header);
        return spekService.saveSpesification(request);
    }

    @GetMapping("/get_device_by/{deviceId}")
    public ResponseEntity<?> getByDeviceId(@RequestHeader Map<String,String> header,
                                           @PathVariable Long deviceId){

        jwtService.filter(header);
        return spekService.getByDeviceId(deviceId);
    }

    @GetMapping("/get_spek_by/{spekId}")
    public ResponseEntity<?> getBySpekId(@RequestHeader Map<String,String> header,
                                         @PathVariable Long spekId){

        jwtService.filter(header);
        return spekService.getBySpekId(spekId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return spekService.getAll();
    }

    //update
    @PutMapping("/update")
    public Map<String, Object> updateDevice (@RequestHeader Map<String,String> header,
                                             @RequestParam(value = "spekId")
                                             Long spekId,
                                             @RequestBody MstSpesifikasiDTO mstSpesifikasiDTO){
        Map<String, Object> mapResult = new HashMap<>();

        MstSpesifikasi mstSpesifikasi= spesificationRepository.findById(spekId).orElse(null);

        mstSpesifikasi.setDeviceId(mstSpesifikasiDTO.getDeviceId());
        mstSpesifikasi.setDeviceName(mstSpesifikasiDTO.getDeviceName());
        mstSpesifikasi.setStorage(mstSpesifikasiDTO.getStorage());
        mstSpesifikasi.setProcessor(mstSpesifikasiDTO.getProcessor());
        mstSpesifikasi.setRam(mstSpesifikasiDTO.getRam());
        mstSpesifikasi.setGraphicCard(mstSpesifikasiDTO.getGraphicCard());

        mapResult.put("message", "Update success");
        mapResult.put("data", spesificationRepository.save(mstSpesifikasi));

        jwtService.filter(header);
        return mapResult;
    }

    @DeleteMapping("/delete/{idSpek}")
    public ResponseEntity<?> deleteSpek(@RequestHeader Map<String,String> header,
                                        @PathVariable Long idSpek){

        jwtService.filter(header);
        return spekService.deleteSpesification(idSpek);
    }
}
