package com.data.sewalaptop.dto.master;

import com.data.sewalaptop.dto.master.MstUserGroupDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    MstUserGroupDTO userGroup;
    private Long userId;
    private Long ugId;
    private Long karyawanId;
    private String userEmail;
    private String password;
}
