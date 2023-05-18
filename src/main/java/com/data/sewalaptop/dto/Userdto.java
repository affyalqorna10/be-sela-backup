package com.data.sewalaptop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class Userdto {
    private Long id;
    @NotBlank(message = "Nama tidak boleh kosong")
    private String name;
    @Email(message = "Email tidak valid")
    private String email;
    @NotBlank(message = "Nama tidak boleh kosong")
    private String phone;
    @NotBlank(message = "Nama tidak boleh kosong")
    private String address;
    private String token;
}
