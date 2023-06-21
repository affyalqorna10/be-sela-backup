package com.data.sewalaptop.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGroupJWTDto {
    private Long ugId;
    private String namaGroup;
}
