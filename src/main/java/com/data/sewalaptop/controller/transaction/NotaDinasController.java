package com.data.sewalaptop.controller.transaction;

import com.data.sewalaptop.dto.master.MstBrandsDTO;
import com.data.sewalaptop.dto.transaction.MstNotaDinasDTO;
import com.data.sewalaptop.service.transaction.NotaDinasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/nodin")
public class NotaDinasController {

    @Autowired
    private NotaDinasService nodinService;

    @PostMapping("/save")
    public ResponseEntity<?> saveNotaDinas(@RequestBody MstNotaDinasDTO request){

        return nodinService.saveNotaDinas(request);
    }

    @DeleteMapping("/delete/{nodinId}")
    public ResponseEntity<?> saveNotaDinas(@PathVariable Long nodinId){
        return nodinService.deleteNodin(nodinId);
    }

    @GetMapping("/get_by/{nodinId}")
    public ResponseEntity<?> getByNodinId(@PathVariable Long nodinId){
        return nodinService.getByNodinId(nodinId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(){
        return nodinService.getAll();
    }
}
