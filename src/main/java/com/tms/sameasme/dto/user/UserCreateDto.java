package com.tms.sameasme.dto.user;

import com.tms.sameasme.model.user.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class UserCreateDto {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private String login;
    private String name;
    private String password;


    private User createUser(UserCreateDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }
}
