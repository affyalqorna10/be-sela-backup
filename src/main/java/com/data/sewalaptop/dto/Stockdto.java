package com.data.sewalaptop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class Stockdto {
    private Long id;
    private Long brand_id;
    private Integer stock;
}
