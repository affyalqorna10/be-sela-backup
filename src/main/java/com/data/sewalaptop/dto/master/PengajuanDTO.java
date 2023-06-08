package com.data.sewalaptop.dto.master;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class PengajuanDTO {
    private Long pengajuanId;
    private Long userId;
    private Long karyawanId;
    private String nikKaryawan;
    private Long divisiId;
    private String noMemo;
    private Date tglPengajuan;
    private String status;
}
