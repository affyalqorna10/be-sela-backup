package com.data.sewalaptop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Spesificationdto {
    private Long id;
    private Long brand_id;
    private String processor;
    private String ram;
    private String storage;
    private String graphic_card;
}
