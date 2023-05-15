package com.data.sewalaptop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class Userdto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String token;
}
