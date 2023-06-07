package com.data.sewalaptop.model.master;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "penyewaan", schema = "public")
public class MstPenyewaan {
    @Id
    @SequenceGenerator(name = "penyewaan_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "penyewaan_seq")
    @Column(name = "penyewaan_id")
    private Long penyewaanId;

    @Column(name = "pengajuan_id")
    private Long pengajuanId;

    @Column(name = "tgl_penyewaan")
    private Date tglPenyewaan;

    @Column(name = "karyawan_id")
    private Long karyawanId;

    @Column(name = "tgl_mulai")
    private Date tglMulai;

    @Column(name = "jatuh_tempo")
    private Date jatuhTempo;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private String status;

}
