package com.data.sewalaptop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nama depan")
    private String nama_depan;

    @Column(name="nama belakang")
    private String nama_belakang;
    
    @Enumerated(EnumType.STRING)
    @Column(name="posisi")
    private ResourceType posisi;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false) 
    private String password;
    
    public enum ResourceType {
        Admin,SuperAdmin
    }
}


