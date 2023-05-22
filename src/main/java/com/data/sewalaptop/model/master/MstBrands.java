package com.data.sewalaptop.model.master;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_brands", schema = "public")
public class MstBrands {
    @Id
    @SequenceGenerator(name = "mst_brand_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_brand_seq")
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "brand_name")
    private String brandName;
}
