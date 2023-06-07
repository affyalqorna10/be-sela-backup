package com.data.sewalaptop.dto.master;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class MstVendorDTO {

    private Long vendorId;
    private String namaVendor;
    private String email;
    private String alamatVendor;
    private String telpVendor;

}
