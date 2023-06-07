package com.data.sewalaptop.model.master;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_karyawan", schema = "public")
public class MstKaryawan {
    @Id
    @SequenceGenerator(name = "mst_karyawan_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_karyawan_seq")
    @Column(name = "karyawan_id")
    private Long karyawanId;

    @Column(name = "divisi_id")
    private Long divisiId;

    @Column(name = "NIK_karyawan")
    private String nikKaryawan;

    @Column(name = "nama_depan")
    private String namaDepan;

    @Column(name = "nama_belakang")
    private String namaBelakang;

    @Column(name = "email_karyawan")
    private String emailKaryawan;

    @Column(name = "alamat_karyawan")
    private String alamatKaryawan;

    @Column(name = "telp_karyawan")
    private String telpKaryawan;
}
