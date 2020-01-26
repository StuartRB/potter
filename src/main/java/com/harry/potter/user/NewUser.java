package com.harry.potter.user;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class NewUser {
    private Long id;
    private String username;
    private String password;
    private boolean blocked;
    private List<String> roles;
}
