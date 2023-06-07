package com.data.sewalaptop.model.master;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_vendor", schema = "public")
public class MstVendor {
    @Id
    @SequenceGenerator(name = "mst_vendor_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_vendor_seq")
    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "nama_vendor")
    private String namaVendor;

    @Column(name = "email")
    private String email;

    @Column(name = "alamat_vendor")
    private String alamatVendor;

    @Column(name = "telp_vendor")
    private String telpVendor;

}
