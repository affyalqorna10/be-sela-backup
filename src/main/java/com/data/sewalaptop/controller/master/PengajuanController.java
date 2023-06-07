package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.PengajuanDTO;
import com.data.sewalaptop.service.master.JwtService;
import com.data.sewalaptop.service.master.PengajuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/pengajuan")
public class PengajuanController {
    @Autowired
    private PengajuanService pengajuanService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> savePengajuan(@RequestHeader Map<String,String> header, @RequestBody PengajuanDTO request){

        jwtService.filter(header);
        return pengajuanService.savePengajuan(request);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return pengajuanService.getAll();
    }
}
