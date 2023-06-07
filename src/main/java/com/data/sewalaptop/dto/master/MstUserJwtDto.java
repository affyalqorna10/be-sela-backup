package com.data.sewalaptop.dto.master;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@Builder
public class MstUserJwtDto {
    private Long userId;
    private Long ugId;
    private String userEmail;
    private UserGroupJWTDto jwtDto;
}
