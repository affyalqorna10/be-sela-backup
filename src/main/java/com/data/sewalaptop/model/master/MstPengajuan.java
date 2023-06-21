package com.data.sewalaptop.model.master;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "mst_pengajuan", schema = "public")
public class MstPengajuan {
    @Id
    @SequenceGenerator(name = "mst_pengajuan_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_pengajuan_seq")
    @Column(name = "pengajuan_id")
    private Long pengajuanId;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "spek_id")
    private Long spekId;

    @Column(name = "storage")
    private String storage;

    @Column(name = "processor")
    private String processor;

    @Column(name = "ram")
    private String ram;

    @Column(name = "graphic_card")
    private String graphicCard;

    @Column(name = "divisi_id")
    private Long divisiId;

    @Column(name = "nama_divisi")
    private String namaDivisi;

    @Column(name = "karyawan_id")
    private Long karyawanId;

    @Column(name = "NIK_karyawan")
    private String nikKaryawan;

    @Column(name = "nama_depan")
    private String namaDepan;

    @Column(name = "nama_belakang")
    private String namaBelakang;

    @Column(name = "no_memo")
    private String noMemo;

    @Column(name = "tgl_pengajuan")
    private Date tglPengajuan;

    @Column(name = "tgl_penerima")
    private Date tglPenerima;

    @Column(name = "status")
    private String status;
}
