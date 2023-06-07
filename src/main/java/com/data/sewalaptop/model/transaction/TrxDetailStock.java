package com.data.sewalaptop.model.transaction;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "trx_detail_stock", schema = "public")
public class TrxDetailStock {
    @Id
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "spek")
    private String spek;

    @Column(name = "stock")
    private Long stock;
}
