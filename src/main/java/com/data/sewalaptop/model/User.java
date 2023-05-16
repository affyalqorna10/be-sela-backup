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
    
    @Column(name="name")
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name="resourceType")
    private ResourceType type;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false) 
    private String password;

    @Column(nullable = false) 
    private String phone;

    @Column(nullable = false) 
    private String address;
    
    public enum ResourceType {
        Admin,SuperAdmin
    }
}


