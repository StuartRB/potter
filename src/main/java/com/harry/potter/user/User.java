package com.harry.potter.user;

import lombok.*;

import javax.persistence.*;

@Entity(name = "USER")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private boolean blocked;

}
