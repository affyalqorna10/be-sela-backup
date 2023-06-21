package com.data.sewalaptop.dto.master;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class MstPengajuanDTO {
    private Long pengajuanId;
    private Long deviceId;
    private String deviceName;
    private Long spekId;
    private String storage;
    private String processor;
    private String ram;
    private String graphicCard;
    private Long divisiId;
    private String namaDivisi;
    private Long karyawanId;
    private String nikKaryawan;
    private String namaDepan;
    private String namaBelakang;
    private String noMemo;
    private Date tglPengajuan;
    private Date tglPenerima;
    private String status;
}
