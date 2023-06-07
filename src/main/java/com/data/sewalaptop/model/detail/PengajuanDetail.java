package com.data.sewalaptop.model.detail;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pengajuan_detail", schema = "public")
public class PengajuanDetail {
    @Id
    @SequenceGenerator(name = "pengajuan_detail_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pengajuan_detail_seq")
    @Column(name = "pengajuandetail_id")
    private Long pengajuandetailId;

    @Column(name = "pengajuan_id")
    private Long pengajuandId;

    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "keterangan")
    private String keterangan;
}
