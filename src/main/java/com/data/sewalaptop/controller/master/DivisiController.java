package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstDivisiDTO;
import com.data.sewalaptop.model.master.MstDevices;
import com.data.sewalaptop.model.master.MstDivisi;
import com.data.sewalaptop.repository.master.DeviceRepository;
import com.data.sewalaptop.repository.master.DivisiRespository;
import com.data.sewalaptop.service.master.DivisiService;
import com.data.sewalaptop.service.master.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/divisi")
public class DivisiController {
    @Autowired
    private DivisiService divisiService;

    @Autowired
    private DivisiRespository divisiRespository;

    //JwtService
    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveDivisi(@RequestHeader Map<String,String> header, @RequestBody MstDivisiDTO request){

        jwtService.filter(header);
        return divisiService.saveDivisi(request);
    }

    @GetMapping("/get_by/{idDivisi}")
    public ResponseEntity<?> getByDivisiId(@RequestHeader Map<String,String> header,@PathVariable Long idDivisi){

        jwtService.filter(header);
        return divisiService.getByDivisiId(idDivisi);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllDivisi(@RequestHeader Map<String,String> header){

        jwtService.filter(header);
        return divisiService.getAllDivisi();
    }

    //Upadte
    @PutMapping("/update")
    public Map<String, Object> updateDevisi (@RequestHeader Map<String, String> header, @RequestParam(value = "divisiId")
                                             Long divisiId, @RequestBody MstDivisiDTO mstDivisiDto){
        Map<String, Object> mapResult = new HashMap<>();

        MstDivisi mstDivisi = divisiRespository.findById(divisiId).orElse(null);

        mstDivisi.setNamaDivisi(mstDivisiDto.getNamaDivisi());

        mapResult.put("message", "Update success");
        mapResult.put("data", divisiRespository.save(mstDivisi));

        jwtService.filter(header);
        return mapResult;

    }

    @DeleteMapping("/delete/{idDivisi}")
    public ResponseEntity<?> saveDivisi(@RequestHeader Map<String,String> header,@PathVariable Long idDivisi) {

        jwtService.filter(header);
        return divisiService.deleteDivisi(idDivisi);
    }


}
