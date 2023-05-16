package com.data.sewalaptop.service;

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
