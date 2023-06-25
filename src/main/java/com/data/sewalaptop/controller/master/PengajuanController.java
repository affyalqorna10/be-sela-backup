package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstDevicesDTO;
import com.data.sewalaptop.dto.master.MstPengajuanDTO;
import com.data.sewalaptop.model.master.MstDevices;
import com.data.sewalaptop.model.master.MstPengajuan;
import com.data.sewalaptop.repository.master.PengajuanRepository;
import com.data.sewalaptop.service.master.JwtService;
import com.data.sewalaptop.service.master.PengajuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/pengajuan")
public class PengajuanController {
    @Autowired
    private PengajuanService pengajuanService;

    @Autowired
    private PengajuanRepository pengajuanRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> savePengajuan(@RequestHeader Map<String,String> header,
                                           @RequestBody MstPengajuanDTO request) {
        jwtService.filter(header);
        return pengajuanService.savePengajuan(request);
    }


    @GetMapping("/get_all_by/{pengajuanId}")
    public ResponseEntity<?> getByAllPengajuanId(@RequestHeader Map<String,String> header,
                                                 @PathVariable Long pengajuanId){

        jwtService.filter(header);
        return pengajuanService.getByAllPengajuanId(pengajuanId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return pengajuanService.getAll();
    }

    //Upadate
    @PutMapping("/update")
    public Map<String, Object> updatePengajuan (@RequestHeader Map<String,String> header,
                                                @RequestParam(value = "pengajuanId")
                                                Long pengajuanId,
                                                @RequestBody MstDevicesDTO mstDevicesDto){
        Map<String, Object> mapResult = new HashMap<>();

        MstPengajuan mstPengajuan = pengajuanRepository.findById(pengajuanId).orElse(null);

        mstPengajuan.setDeviceName(mstDevicesDto.getDeviceName());

        mapResult.put("message", "Update success");
        mapResult.put("data", pengajuanRepository.save(mstPengajuan));

        jwtService.filter(header);
        return mapResult;
    }


    @DeleteMapping("/delete/{pengajuanId}")
    public ResponseEntity<?> savePengajuan(@RequestHeader Map<String,String> header,
                                           @PathVariable Long pengajuanId){

        jwtService.filter(header);
        return pengajuanService.deletePengajuan(pengajuanId);
    }
}
