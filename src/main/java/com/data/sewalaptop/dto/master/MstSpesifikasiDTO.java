package com.data.sewalaptop.dto.master;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstSpesifikasiDTO {
    private Long spekId;
    private Long deviceId;
    private String deviceName;
    private String storage;
    private String processor;
    private String ram;
    private String graphicCard;
}