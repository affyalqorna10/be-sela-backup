package com.data.sewalaptop.dto.master;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class MstPenyewaanDTO {
    private Long penyewaanId;
    private Long pengajuanId;
    private Date tglPenyewaan;
    private Long karyawanId;
    private Date tglMulai;
    private Date jatuhTempo;
    private Long userId;
    private String status;
}
