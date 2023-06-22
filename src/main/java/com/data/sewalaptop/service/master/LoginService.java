package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.MstLoginDTO;
import com.data.sewalaptop.model.master.MstUser;
import com.data.sewalaptop.repository.master.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepo;

    private String passEncript(String pass) {
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

    public ResponseEntity<?> login(MstLoginDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstUser user = userRepo.findByEmail(requestDTO.getUserEmail());
        if (user == null){
            response.setCode("401");
            response.setMessage("Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String pas=passEncript(requestDTO.getPassword());
        if (!pas.equals((user.getPassword()))){
            response.setCode("401");
            response.setMessage("Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String token = Jwts.builder()
                .setSubject(requestDTO.getPassword())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, "SecretKey")
                .compact();
        user.setToken(token);
        userRepo.save(user);

//        HashMap<String,String> jsonResponse = new HashMap<>();
//        jsonResponse.put("token",token);
//        jsonResponse.put("user_group",user.getUgId().toString());

        response.setCode("200");
        response.setData(token);
        response.setMessage("success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
