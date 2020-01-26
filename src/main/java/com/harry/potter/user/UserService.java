package com.harry.potter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NewUserToUserConverter newUserToUserConverter;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Long addUser(NewUser newUser){
        return userRepository.save(newUserToUserConverter.convert(newUser)).getId();
    }
}
