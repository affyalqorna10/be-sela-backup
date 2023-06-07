package com.data.sewalaptop.model.master;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_user", schema = "public")
public class MstUser {
    @Id
    @SequenceGenerator(name = "mst_user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_user_seq")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "ug_id")
    private Long ugId;

    @Column(name = "divisi_id")
    private Long divisiId;

    @Column(name = "NIK_user")
    private String nikUser;

    @Column(name = "nama_depan")
    private String namaDepan;

    @Column(name = "nama_belakang")
    private String namaBelakang;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "password")
    private String password;

    @Column(name = "telp_user")
    private String telpUser;

    @Column(name = "alamat_user")
    private String alamatUser;

    @Column(name = "status")
    private String status;

    @Column(name = "token")
    private String token;
}
