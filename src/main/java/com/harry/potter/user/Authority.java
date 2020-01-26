package com.harry.potter.user;

import lombok.*;

import javax.persistence.*;

@Entity(name = "AUTHORITY")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String authority;

}
