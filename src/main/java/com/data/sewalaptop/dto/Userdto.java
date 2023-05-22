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
    private String nama_depan;
    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama_belakang;
    @Email(message = "Email tidak valid")
    private String email;
    @NotBlank(message = "Nama posisi tidak boleh kosong")
    private ResourceType type;
    
    private String token;

    public enum ResourceType {
        Admin,SuperAdmin
    }
}
