package com.data.sewalaptop.model.master;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_user", schema = "public")
public class MstUser {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "nama_belakang")
    private String namaBelakang;

    @Column(name = "nama_depan")
    private String namaDepan;

    @Column(name = "password")
    private String password;

    @Column(name = "user_posisi")
    private Long userPosisi;

    @Column(name = "user_jwt")
    private Long userJwt;
}
