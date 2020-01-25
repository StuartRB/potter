package com.harry.potter.user;

import lombok.*;

import javax.persistence.*;

@Entity(name = "AUTHORITY")
@Data
class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String authority;

}
