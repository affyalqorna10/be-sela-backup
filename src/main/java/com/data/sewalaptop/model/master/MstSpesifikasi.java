package com.data.sewalaptop.model.master;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_spesifikasi", schema = "public")
public class MstSpesifikasi {
    @Id
    @SequenceGenerator(name = "mst_spek_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_spek_seq")
    @Column(name = "spek_id")
    private Long spekId;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "spesifikasi", columnDefinition = "TEXT", nullable = false)
    private String spek_name;

}
