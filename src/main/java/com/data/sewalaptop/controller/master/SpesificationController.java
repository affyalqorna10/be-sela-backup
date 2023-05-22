package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.service.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/spesification")
public class SpesificationController {
    @Autowired
    private SpesificationService spekService;

    @PostMapping("/save")
    public ResponseEntity<?> saveSpesification(@RequestBody MstSpesifikasiDTO request){

        return spekService.saveSpesification(request);
    }

    @PostMapping("/delete/{idSpek}")
    public ResponseEntity<?> saveBrand(@PathVariable Long idSpek){

        return spekService.deleteSpesification(idSpek);
    }
}
