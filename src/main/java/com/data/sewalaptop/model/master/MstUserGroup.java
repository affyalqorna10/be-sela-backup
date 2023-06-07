package com.data.sewalaptop.model.master;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_user_groups", schema = "public")
public class MstUserGroup {
    @Id
    @SequenceGenerator(name = "mst_user_group_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_user_group_seq")
    @Column(name = "ug_id")
    private Long ugId;

    @Column(name = "nama_group")
    private String namaGroup;
}
