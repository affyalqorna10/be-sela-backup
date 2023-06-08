package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstLoginDTO;
import com.data.sewalaptop.service.master.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/save")
    public ResponseEntity<?> savelogin(@RequestBody MstLoginDTO request){
        return loginService.login(request);
    }


}
