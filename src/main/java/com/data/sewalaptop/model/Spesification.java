package com.data.sewalaptop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spesifications")
@Getter
@Setter
public class Spesification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="brand_id")
    private Integer brand_id;
    private String processor;
    private String ram;
    private String storage;
    private String graphic_card;

}
