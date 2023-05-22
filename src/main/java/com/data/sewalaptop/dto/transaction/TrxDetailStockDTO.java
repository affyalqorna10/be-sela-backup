package com.data.sewalaptop.dto.transaction;

import lombok.*;

@Getter @Setter
public class TrxDetailStockDTO {
    private Long brandId;
    private String brandName;
    private String storage;
    private String processor;
    private String ram;
    private String graphicCard;
    private Integer stock;
}
