package com.data.sewalaptop.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstKaryawanDTO {
    private Long karyawanId;
    private Long divisiId;
    private String nikKaryawan;
    private String namaDepan;
    private String namaBelakang;
    private String emailKaryawan;
    private String alamatKaryawan;;
    private String telpKaryawan;
}
