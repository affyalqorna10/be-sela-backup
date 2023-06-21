package com.data.sewalaptop.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstUserJwtDto {
    private Long userId;
    private Long ugId;
    private String userEmail;
    private UserGroupJWTDto jwtDto;
}
