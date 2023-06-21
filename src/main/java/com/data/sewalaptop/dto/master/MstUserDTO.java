package com.data.sewalaptop.dto.master;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstUserDTO {
    private Long userId;
    private Long ugId;
    private Long karyawanId;
    private String userEmail;
    private String password;
}
