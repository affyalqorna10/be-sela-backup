package com.data.sewalaptop.model.master;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pengajuan", schema = "public")
public class Pengajuan {
    @Id
    @SequenceGenerator(name = "pengajuan_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pengajuan_seq")
    @Column(name = "pengajuan_id")
    private Long pengajuanId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "karyawan_id")
    private Long karyawanId;

    @Column(name = "NIK_karyawan")
    private String nikKaryawan;

    @Column(name = "divisi_id")
    private Long divisiId;

    @Column(name = "no_memo")
    private String noMemo;

    @Column(name = "tgl_pengajuan")
    private Date tglPengajuan;

    @Column(name = "status")
    private String status;
}
