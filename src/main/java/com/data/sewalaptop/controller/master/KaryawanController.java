package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstKaryawanDTO;
import com.data.sewalaptop.service.master.JwtService;
import com.data.sewalaptop.service.master.KaryawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/karyawan")
public class KaryawanController {
    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveKaryawan(@RequestBody MstKaryawanDTO request){

        return karyawanService.savekaryawan(request);
    }

    @DeleteMapping("/delete/{karyawanId}")
    public ResponseEntity<?> saveKaryawan(@RequestHeader Map<String,String> header, @PathVariable Long karyawanId){

        jwtService.filter(header);
        return karyawanService.deleteKaryawan(karyawanId);
    }

    @GetMapping("/get_karyawan_by/{karyawanId}")
    public ResponseEntity<?> getBykaryawanId(@RequestHeader Map<String,String> header,@PathVariable Long karyawanId){

        jwtService.filter(header);
        return karyawanService.getBykaryawanId(karyawanId);
    }

    @GetMapping("/get_divisi_by/{idDivisi}")
    public ResponseEntity<?> getByDivisiId(@PathVariable Long idDivisi){

        return karyawanService.getByDivisiId(idDivisi);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return karyawanService.getAll();
    }
}
