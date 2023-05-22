package com.data.sewalaptop.dto.transaction;

import lombok.*;

@Getter @Setter
public class TrxCustomerStockDTO {
    private Long id;
    private String barcode;
    private Long spesifikasi;
    private String status;
    private String customer;
}
