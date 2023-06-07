package com.data.sewalaptop.model.master;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "oauths", schema = "public")
public class OAuth {
    @Id
    @SequenceGenerator(name = "oauths_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="oauths_seq")
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(name = "user_token")
    private String userToken;

}
