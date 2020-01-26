package com.harry.potter.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewUserToUserConverter {

    public User convert(NewUser newUser){
        return User.builder()
                .id(newUser.getId())
                .username(newUser.getUsername())
                .password(newUser.getPassword())
                .blocked(false)
                .authorities(buildAuthorities(newUser.getRoles()))
                .build();
    }

    private List<Authority> buildAuthorities(List<String> roles) {
        List<Authority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(Authority.builder().authority(role).build()));
        return authorities;
    }
}
