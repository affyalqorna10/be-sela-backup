package com.data.sewalaptop.dto.master;
import lombok.*;

@Getter @Setter
public class MstSpesifikasiDTO {
    private Long spekId;
    private Long deviceId;
    private String deviceName;
    private String storage;
    private String processor;
    private String ram;
    private String graphicCard;
}