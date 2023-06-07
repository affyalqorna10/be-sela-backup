package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.ResponseDTO;
import com.data.sewalaptop.dto.master.MstUserGroupDTO;
import com.data.sewalaptop.dto.master.MstUserJwtDto;
import com.data.sewalaptop.dto.master.UserGroupJWTDto;
import com.data.sewalaptop.exception.JwtException;
import com.data.sewalaptop.exception.NotFoundUserGroup;
import com.data.sewalaptop.model.master.MstUser;
import com.data.sewalaptop.model.master.MstUserGroup;
import com.data.sewalaptop.repository.master.UserGroupRepository;
import com.data.sewalaptop.repository.master.UserRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class JwtService {
    @Autowired
    UserRepository repo;
    @Autowired
    UserGroupRepository ug;
    public MstUserJwtDto filter(Map<String,String> header){
           // log.info(String.format("%s",new Gson().toJson(header)));
           String bearer = header.get("authorization");
           String split[] = bearer.split(" ");
           bearer = split[1];
           log.info(bearer);
           MstUser user = repo.findByToken(bearer);
           if(user==null){
               throw new JwtException();
           }
           Long idgroup = user.getUgId();
           Optional<MstUserGroup> u =  ug.findById(idgroup)    ;
           if(!u.isPresent()){
               throw new NotFoundUserGroup();
           }
        UserGroupJWTDto userGroup = new UserGroupJWTDto();
                userGroup.setNamaGroup(u.get().getNamaGroup());
                userGroup.setUgId(u.get().getUgId());

           MstUserJwtDto rt = MstUserJwtDto.builder()
                   .userId(user.getUserId())
                   .userEmail(user.getUserEmail())
                   .jwtDto(userGroup)
                   .ugId(user.getUgId()).build();
           log.info(String.format("%s",new Gson().toJson(rt)));

           return rt;

    }

    private String getBearerToken(Map<String,String> bearer){
        return bearer.get("Authorization");
    }
}
