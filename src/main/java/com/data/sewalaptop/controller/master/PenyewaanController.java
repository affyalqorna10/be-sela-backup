package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstPenyewaanDTO;
import com.data.sewalaptop.service.master.JwtService;
import com.data.sewalaptop.service.master.PenyewaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/penyewaan")
public class PenyewaanController {

    @Autowired
    private PenyewaanService penyewaanService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> savePenyewaan(@RequestHeader Map<String,String> header, @RequestBody MstPenyewaanDTO request){

        jwtService.filter(header);
        return penyewaanService.savePenyewaan(request);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return penyewaanService.getAll();
    }
}
