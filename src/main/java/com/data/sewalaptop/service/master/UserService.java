package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.dto.transaction.PengajuanResponse;
import com.data.sewalaptop.model.master.MstKaryawan;
import com.data.sewalaptop.model.master.MstUser;
import com.data.sewalaptop.model.master.MstUserGroup;
import com.data.sewalaptop.model.master.OAuth;
import com.data.sewalaptop.repository.master.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.data.sewalaptop.common.Checker.isNullStr;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserGroupRepository userGroupRepo;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private KaryawanRepository karyawanRepo;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private OAuthRepository oauthRepo;

    public ResponseEntity<?> saveUser(MstUserDTO requestDTO){

        return createUser(requestDTO);
    }

    public ResponseEntity<?> deleteUser(Long userId){
        ResponseDTO response = new ResponseDTO();
        MstUser user = userRepo.findByUserId(userId);
        if (user == null){
            response.setCode("204");
            response.setMessage("User ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userRepo.delete(user);

        response.setCode("200");
        response.setData(null);
        response.setMessage("User Id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createUser(MstUserDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        MstUser userEntity = new MstUser();
        MstUser user = userRepo.findByUserId(requestDTO.getUserId());
        if (user == null) {
            if (isNullStr(requestDTO.getUserEmail())) {
                if (isNullStr(requestDTO.getPassword())) {
                    userEntity.setUgId(requestDTO.getUgId());
                    userEntity.setKaryawanId(requestDTO.getKaryawanId());
                    userEntity.setUserEmail(requestDTO.getUserEmail());
                    userEntity.setPassword(passEncript(requestDTO.getPassword()));

                    userRepo.save(userEntity);

                    response.setCode("201");
                    response.setData(null);
                    response.setMessage("User has been saved successfully");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                    }
                response.setCode("204");
                response.setMessage("Password cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("User Email cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setMessage("Data Spesification already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstUser> userList = userRepo.findAll();
        List<UserResponse> responses = new ArrayList<>();

        for(MstUser user:userList){
            UserResponse resp = new UserResponse();
            resp.setUserId(user.getUserId());
            resp.setUgId(user.getUgId());
            resp.setKaryawanId(user.getKaryawanId());
            resp.setUserEmail(user.getUserEmail());
            resp.setPassword(user.getPassword());

            MstUserGroup userGroup = userGroupRepo.findByGroupId(user.getUgId());
            MstUserGroupDTO dtouserGroup = new MstUserGroupDTO();
            dtouserGroup.setNamaGroup(userGroup.getNamaGroup());

            resp.setUserGroup(dtouserGroup);

            responses.add(resp);

        }

        response.setCode("200");
        response.setData(responses);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByUserId(Long userId){
        ResponseDTO response = new ResponseDTO();
        MstUser user = userRepo.findByUserId(userId);
        UserResponse resp = new UserResponse();
        if(user == null){
            response.setCode("400");
            response.setMessage("Err:User tidak ditemukan");
            response.setData(null);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        resp.setUserId(user.getUserId());
        resp.setUgId(user.getUgId());
        resp.setKaryawanId(user.getKaryawanId());
        resp.setUserEmail(user.getUserEmail());
        resp.setPassword(user.getPassword());

        MstUserGroup userGroup = userGroupRepo.findByGroupId(user.getUgId());
        MstUserGroupDTO dtouserGroup = new MstUserGroupDTO();
        dtouserGroup.setNamaGroup(userGroup.getNamaGroup());

        resp.setUserGroup(dtouserGroup);
        if (user == null){
            response.setCode("204");
            response.setMessage("User ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("200");
        response.setData(resp);
        response.setMessage("Get Data By User Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String passEncript(String pass){
        MessageDigest md;
        String passEnc;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passEnc = s.toString();
        } catch (Exception e) {
            return "Password Encryption Error";
        }
        return passEnc;
    }

}
