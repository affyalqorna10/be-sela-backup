package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstLoginDTO;
import com.data.sewalaptop.dto.master.MstUserDTO;
import com.data.sewalaptop.service.master.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody MstUserDTO request){
        return userService.saveUser(request);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> saveBrand(@PathVariable Long userId){

        return userService.deleteUser(userId);
    }

    @GetMapping("/get_by/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Long userId){
        return userService.getByUserId(userId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(){
        return userService.getAll();
    }

}
