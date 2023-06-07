package com.data.sewalaptop.model.transaction;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "nota_dinas", schema = "public")
public class MstNotaDinas {
    @Id
    @SequenceGenerator(name = "nota_dinas_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_dinas_seq")
    @Column(name = "nodin_id")
    private Long nodinId;

    @Column(name = "karyawan_id")
    private Long karyawanId;

    @Column(name = "NIK")
    private String NIK;

    @Column(name = "nota_dinas")
    private String notaDinas;

    @Column(name = "tgl_mulai")
    private Date tglMulai;

    @Column(name = "jatuh_tempo")
    private Date jatuhTempo;

}
