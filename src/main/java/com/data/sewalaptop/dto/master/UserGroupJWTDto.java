package com.data.sewalaptop.dto.master;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class UserGroupJWTDto {
    private Long ugId;
    private String namaGroup;
}
