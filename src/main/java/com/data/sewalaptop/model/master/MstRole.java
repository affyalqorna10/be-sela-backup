package com.data.sewalaptop.model.master;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_role", schema = "public")
public class MstRole {
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;
}
