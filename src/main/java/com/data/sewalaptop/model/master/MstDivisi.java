package com.data.sewalaptop.model.master;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_divisis", schema = "public")
public class MstDivisi {
    @Id
    @SequenceGenerator(name = "mst_divisi_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_divisi_seq")
    @Column(name = "divisi_id")
    private Long divisiId;

    @Column(name = "nama_divisi")
    private String namaDivisi;
}
