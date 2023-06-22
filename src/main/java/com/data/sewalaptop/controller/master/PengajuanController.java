package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstPengajuanDTO;
import com.data.sewalaptop.service.master.JwtService;
import com.data.sewalaptop.service.master.PengajuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/pengajuan")
public class PengajuanController {
    @Autowired
    private PengajuanService pengajuanService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> savePengajuan(@RequestHeader Map<String,String> header, @RequestBody MstPengajuanDTO request){

        jwtService.filter(header);
        return pengajuanService.savePengajuan(request);
    }

    @DeleteMapping("/delete/{pengajuanId}")
    public ResponseEntity<?> savePengajuan(@RequestHeader Map<String,String> header, @PathVariable Long pengajuanId){

        jwtService.filter(header);
        return pengajuanService.deletePengajuan(pengajuanId);
    }

    @GetMapping("/get_all_by/{pengajuanId}")
    public ResponseEntity<?> getByAllPengajuanId(@RequestHeader Map<String,String> header, @PathVariable Long pengajuanId){

        jwtService.filter(header);
        return pengajuanService.getByAllPengajuanId(pengajuanId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return pengajuanService.getAll();
    }
}
