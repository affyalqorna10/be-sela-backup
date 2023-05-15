package com.data.sewalaptop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {
    private String code;
    private String message;
    private Object data;
}
