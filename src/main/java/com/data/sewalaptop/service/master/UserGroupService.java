package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.MstUserGroupDTO;
import com.data.sewalaptop.model.master.MstUserGroup;
import com.data.sewalaptop.repository.master.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.data.sewalaptop.common.Checker.isNullStr;
import static com.data.sewalaptop.common.Checker.validStrOnly;

@Service
public class UserGroupService {
    @Autowired
    private UserGroupRepository usrGropRepo;

    public ResponseEntity<?> saveUserGroup(MstUserGroupDTO requestDTO){
        if (requestDTO.getUgId() == null){
            return createUserGroup(requestDTO);
        }
        return updateUserGroup(requestDTO);
    }

    public ResponseEntity<?> deleteUserGroup(Long ugId){
        ResponseDTO response = new ResponseDTO();
        MstUserGroup userGroups = usrGropRepo.findByGroupId(ugId);
        if (userGroups == null){
            response.setCode("204");
            response.setMessage("User Group ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        usrGropRepo.delete(userGroups);

        response.setCode("200");
        response.setData(null);
        response.setMessage("User Group id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByGroupId(Long ugId){
        ResponseDTO response = new ResponseDTO();
        MstUserGroup userGroups = usrGropRepo.findByGroupId(ugId);
        if (userGroups == null){
            response.setCode("204");
            response.setMessage("User Group ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(userGroups);
        response.setMessage("Get Data By User Group Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllGroup(){
        ResponseDTO response = new ResponseDTO();
        List<MstUserGroup> UserGroupsList = usrGropRepo.findAll();

        response.setCode("200");
        response.setData(UserGroupsList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createUserGroup(MstUserGroupDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstUserGroup userGroups = usrGropRepo.findByNamaGroup(requestDTO.getNamaGroup());
        if (userGroups == null) {
            MstUserGroup userGroup = new MstUserGroup();
            //Validate Nama Group null && string only
            if (isNullStr(requestDTO.getNamaGroup())){

                    userGroup.setNamaGroup(requestDTO.getNamaGroup().toUpperCase());
                    usrGropRepo.save(userGroup);

                    response.setCode("201");
                    response.setData(null);
                    response.setMessage("User Group has been saved successfully");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }

            response.setCode("204");
            response.setMessage("Nama Group cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setData(null);
        response.setMessage("User Group already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> updateUserGroup(MstUserGroupDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstUserGroup userGroups = usrGropRepo.findByGroupId(requestDTO.getUgId());

        if (Objects.equals(userGroups.getUgId(), requestDTO.getUgId())){
            MstUserGroup userGroup = new MstUserGroup();
            if (isNullStr(requestDTO.getNamaGroup())){
                    userGroup.setUgId(requestDTO.getUgId());
                    userGroup.setNamaGroup(requestDTO.getNamaGroup());

                    usrGropRepo.save(userGroup);

                    response.setCode("201");
                    response.setData(null);
                    response.setMessage("User Group has been saved successfully");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
            response.setCode("204");
            response.setMessage("Nama Group cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setData(null);
        response.setMessage("User Group already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
