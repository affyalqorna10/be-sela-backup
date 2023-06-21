package com.data.sewalaptop.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstUserGroupDTO {

    private Long ugId;
    private String namaGroup;

}
