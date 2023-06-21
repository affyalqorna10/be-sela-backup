package com.data.sewalaptop.dto.master;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstStockDTO {
    private Long stockId;
    private Long deviceId;
    private Long spekId;
    private Integer qty;
}
