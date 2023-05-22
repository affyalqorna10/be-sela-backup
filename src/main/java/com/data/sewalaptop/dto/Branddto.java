package com.data.sewalaptop.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Branddto {
    private Long id;

    //@NotBlank(message = "Nama tidak boleh kosong")
    private String brand_name;
}
