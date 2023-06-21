package com.data.sewalaptop.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstDevicesDTO {
    private Long deviceId;
    private String deviceName;
}
