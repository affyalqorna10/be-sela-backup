package com.data.sewalaptop.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstVendorDTO {

    private Long vendorId;
    private String namaVendor;
    private String email;
    private String alamatVendor;
    private String telpVendor;

}
