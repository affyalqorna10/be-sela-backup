package com.data.sewalaptop.dto.master;
import lombok.*;

@Getter @Setter
public class MstUserDTO {
    private Long userId;
    private String userEmail;
    private String namaBelakang;
    private String namaDepan;
    private String password;
    private String userPosisi;
}
