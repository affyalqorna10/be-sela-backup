package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.service.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping("/save")
    public ResponseEntity<?> saveBrand(@RequestBody MstBrandsDTO request){

        return brandService.saveBrand(request);
    }

    @PostMapping("/delete/{idBrand}")
    public ResponseEntity<?> saveBrand(@PathVariable Long idBrand){

        return brandService.deleteBrand(idBrand);
    }

    @GetMapping("/get_by/{idBrand}")
    public ResponseEntity<?> getByBrandId(@PathVariable Long idBrand){

        return brandService.getByBrandId(idBrand);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllBrand(){

        return brandService.getAllBrand();
    }
}
