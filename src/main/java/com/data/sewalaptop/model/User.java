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

    private String email;
    private String password;
    private String phone;
    private String address;
    
    public enum ResourceType {
        Admin,Customer
    }
}


