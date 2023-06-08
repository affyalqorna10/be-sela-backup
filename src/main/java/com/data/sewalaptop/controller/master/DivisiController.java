package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstDivisiDTO;
import com.data.sewalaptop.service.master.DivisiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/divisi")
public class DivisiController {
    @Autowired
    private DivisiService divisiService;

    @PostMapping("/save")
    public ResponseEntity<?> saveDivisi(@RequestBody MstDivisiDTO request){

        return divisiService.saveDivisi(request);
    }

    @DeleteMapping("/delete/{idDivisi}")
    public ResponseEntity<?> saveDivisi(@PathVariable Long idDivisi) {

        return divisiService.deleteDivisi(idDivisi);
    }

    @GetMapping("/get_by/{idDivisi}")
    public ResponseEntity<?> getByDivisiId(@PathVariable Long idDivisi){

        return divisiService.getByDivisiId(idDivisi);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllDivisi(){

        return divisiService.getAllDivisi();
    }
}
