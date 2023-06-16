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

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "storage")
    private String storage;

    @Column(name = "processor")
    private String processor;

    @Column(name = "ram")
    private String ram;

    @Column(name = "graphic_card")
    private String graphicCard;

}
