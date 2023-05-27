package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.*;
import com.data.sewalaptop.dto.master.MstRoleDTO;
import com.data.sewalaptop.model.master.MstRole;
import com.data.sewalaptop.repository.master.RoleRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

import static com.data.sewalaptop.common.Checker.*;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;

    public ResponseEntity<?> saveRole(MstRoleDTO requestDTO) {
        if (requestDTO.getRoleName() == null) {
            return createRole(requestDTO);
        }
        return updateRole(requestDTO);
    }

    public ResponseEntity<?> deleteRole(Long roleId){
        ResponseDTO response = new ResponseDTO();
        MstRole role = roleRepo.findByRoleId(roleId);
        if (role == null){
            response.setCode("204");
            response.setMessage("Brand ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        roleRepo.delete(role);

        response.setCode("200");
        response.setData(null);
        response.setMessage("Brand id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByRoleId(Long roleId){
        ResponseDTO response = new ResponseDTO();
        MstRole role = roleRepo.findByRoleId(roleId);

        response.setCode("200");
        response.setData(role);
        response.setMessage("Get Data By Brand Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllRole(){
        ResponseDTO response = new ResponseDTO();
        List<MstRole> roleList = roleRepo.findAll();

        response.setCode("200");
        response.setData(roleList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createRole(MstRoleDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        MstRole roles = roleRepo.findByRoleName(requestDTO.getRoleName());
        if (roles == null) {
            MstRole roleEntity = new MstRole();
            // Validate Brand Name null && string only
            if (isNullStr(requestDTO.getRoleName())) {
                roleEntity.setRoleName(requestDTO.getRoleName());
                roleRepo.save(roleEntity);

                response.setCode("201");
                response.setData(null);
                response.setMessage("Role has been saved successfully");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            response.setCode("204");
            response.setMessage("Role name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("409");
        response.setData(null);
        response.setMessage("Role already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> updateRole(MstRoleDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        MstRole roles = roleRepo.findByRoleName(requestDTO.getRoleName());

        if (Objects.equals(roles.getRoleName(), requestDTO.getRoleName())) {
            MstRole roleEntity = new MstRole();
            if (isNullStr(requestDTO.getRoleName())) {
                roleEntity.setRoleName(requestDTO.getRoleName());

                roleRepo.save(roleEntity);

                response.setCode("201");
                response.setData(null);
                response.setMessage("Role has been saved successfully");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }

            response.setCode("204");
            response.setMessage("Role name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("409");
        response.setData(null);
        response.setMessage("Role already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
