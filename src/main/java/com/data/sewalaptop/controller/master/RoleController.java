package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstRoleDTO;
import com.data.sewalaptop.service.master.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<?> saveBrand(@RequestBody MstRoleDTO request){

        return roleService.saveRole(request);
    }

    @PostMapping("/delete/{idRole}")
    public ResponseEntity<?> saveBrand(@PathVariable Long idRole){

        return roleService.deleteRole(idRole);
    }

    @GetMapping("/get_by/{idRole}")
    public ResponseEntity<?> getByBrandId(@PathVariable Long idRole){

        return roleService.getByRoleId(idRole);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllBrand(){

        return roleService.getAllRole();
    }
}
