package com.data.sewalaptop.model.transaction;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "trx_customer_stock", schema = "public")
public class TrxCustomerStock {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "brand_id")
    private String brandId;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "spesifikasi")
    private Long spesifikasi;

    @Column(name = "status")
    private String status;

    @Column(name = "customer")
    private String customer;
}
