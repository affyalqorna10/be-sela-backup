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

public class Fiturdetaildto {
    private Long id;

    private String processor;

    private String ram;

    private String storage;

    private String graphic_card;

    private String status;

    private String code;

}
