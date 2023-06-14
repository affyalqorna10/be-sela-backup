package com.data.sewalaptop.dto.master;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class PengajuanDTO {
    private Long pengajuanId;
    private Long spekId;
    private Long karyawanId;
    private String noMemo;
    private Date tglPengajuan;
    private Date tglPenerima;
    private String status;
}
