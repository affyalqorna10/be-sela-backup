package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.service.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    //JwtService
    @Autowired JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveBrand(@RequestHeader Map<String,String> header, @RequestBody MstBrandsDTO request){

        jwtService.filter(header);
        return brandService.saveBrand(request);
    }

    @DeleteMapping("/delete/{idBrand}")
    public ResponseEntity<?> saveBrand(@RequestHeader Map<String,String> header, @PathVariable Long idBrand){

        jwtService.filter(header);
        return brandService.deleteBrand(idBrand);
    }

    @GetMapping("/get_by/{idBrand}")
    public ResponseEntity<?> getByBrandId(@RequestHeader Map<String,String> header,@PathVariable Long idBrand){

        jwtService.filter(header);
        return brandService.getByBrandId(idBrand);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllBrand(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return brandService.getAllBrand();
    }
}
