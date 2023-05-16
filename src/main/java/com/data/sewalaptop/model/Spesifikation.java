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
@Table(name = "spesifikations")
@Getter
@Setter
public class Spesifikation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name_spesifikation")
    private String name_spesifikation;
    private Integer brand_id;
    private String RAM;
    private String storage;
    private String graphic_card;
}
