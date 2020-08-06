package com.tms.sameasme.service.user;

import com.tms.sameasme.dto.user.UpdateUserDto;
import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.model.user.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User findUserByLogin(String login);
    User findUserById(Long id);
    List<User> deleteById(Long id);
    List<User> findAll();
    boolean addRoleToUser(Long id, ERole role);
    boolean deleteRoleToUser(Long id, ERole role);
    User updateUser(UpdateUserDto newUser);
}
