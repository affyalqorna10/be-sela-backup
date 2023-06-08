package com.data.sewalaptop.model.master;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_stock", schema = "public")
public class MstStock {
    @Id
    @SequenceGenerator(name = "mst_stock_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_stock_seq")
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "code_qr")
    private String codeQr;

    @Column(name = "status")
    private String status;
}
