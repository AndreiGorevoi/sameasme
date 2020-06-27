package com.tms.sameasme.service.user;

import com.tms.sameasme.model.user.User;

public interface UserService {
    User addUser(User user);
    User findUserByLogin(String login);
    User findUserById(Long id);
}
