package com.data.sewalaptop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SMdto {

    private Long id;

    private String brand_name;

    private String idmodel;

    private String processor;

    private String ram;

    private String storage;

    private String graphic_card;

    private Integer stock;

}
