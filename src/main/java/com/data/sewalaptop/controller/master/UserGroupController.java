package com.data.sewalaptop.controller.master;

import com.data.sewalaptop.dto.master.MstUserGroupDTO;
import com.data.sewalaptop.service.master.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user_group")
public class UserGroupController {
    @Autowired
    private UserGroupService usergroupService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUserGroup(@RequestBody MstUserGroupDTO request) {

        return usergroupService.saveUserGroup(request);
    }

    @DeleteMapping("/delete/{idGroup}")
    public ResponseEntity<?> saveUserGroup(@PathVariable Long idGroup) {

        return usergroupService.deleteUserGroup(idGroup);
    }

    @GetMapping("/get_by/{idGroup}")
    public ResponseEntity<?> getByUserGroupId(@PathVariable Long idGroup){

        return usergroupService.getByGroupId(idGroup);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllGroup(){

        return usergroupService.getAllGroup();
    }
}
