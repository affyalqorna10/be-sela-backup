package com.data.sewalaptop.controller.detail;

import com.data.sewalaptop.dto.detail.PengajuanDetailDTO;
import com.data.sewalaptop.service.detail.PengajuanDetailService;
import com.data.sewalaptop.service.master.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/pengajuan-detail")
public class PengajuanDetailController {
    @Autowired
    private PengajuanDetailService pengajuanDetailService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> savePengajuanDetail(@RequestHeader Map<String,String> header, @RequestBody PengajuanDetailDTO request) {

        jwtService.filter(header);
        return pengajuanDetailService.savePengajuanDetail(request);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return pengajuanDetailService.getAll();
    }
}
