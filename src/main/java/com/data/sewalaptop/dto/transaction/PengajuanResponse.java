package com.data.sewalaptop.dto.transaction;

import com.data.sewalaptop.dto.master.MstDevicesDTO;
import com.data.sewalaptop.dto.master.MstDivisiDTO;
import com.data.sewalaptop.dto.master.MstKaryawanDTO;
import com.data.sewalaptop.dto.master.MstSpesifikasiDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PengajuanResponse {
    private MstSpesifikasiDTO spesifikasi;
    private MstDevicesDTO device;
    private MstKaryawanDTO karyawan;
    private MstDivisiDTO divisi;
    private Long pengajuanId;
    private Long spekId;
    private Long karyawanId;
    private String noMemo;
    private Date tglPengajuan;
    private Date tglPenerima;
    private String status;
}
