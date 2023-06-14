package com.data.sewalaptop.model.transaction;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "trx_detail_stock", schema = "public")
public class TrxDetailStock {
    @Id
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

    @Column(name = "stock")
    private Integer stock;
}
