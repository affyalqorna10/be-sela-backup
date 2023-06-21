package com.data.sewalaptop.dto.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstNotaDinasDTO {

    private Long nodinId;
    private Long karyawanId;
    private String notaDinas;
    private Date tglMulai;
    private Date jatuhTempo;
    private String NIK;
}
