package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstVendorDTO;
import com.data.sewalaptop.service.master.JwtService;
import com.data.sewalaptop.service.master.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveVendor(@RequestHeader Map<String,String> header, @RequestBody MstVendorDTO request){

        jwtService.filter(header);
        return vendorService.saveVendor(request);
    }

    @DeleteMapping("/delete/{vendorId}")
    public ResponseEntity<?> saveVendor(@RequestHeader Map<String,String> header, @PathVariable Long vendorId){

        jwtService.filter(header);
        return vendorService.deleteVendor(vendorId);
    }

    @GetMapping("/get_by/{vendorId}")
    public ResponseEntity<?> getByVendorId(@RequestHeader Map<String,String> header, @PathVariable Long vendorId){

        jwtService.filter(header);
        return vendorService.getByVendorId(vendorId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllVendor(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return vendorService.getAll();
    }

    @GetMapping("/get_by_nama/{namaVendor}")
    public ResponseEntity<?> getByNamaVendor(@RequestHeader Map<String,String> header, @PathVariable String namaVendor){

        jwtService.filter(header);
        return vendorService.getByNamaVendor(namaVendor);
    }
}
