package com.tms.sameasme.service.user;

import com.tms.sameasme.dto.user.UpdateUserDto;
import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.model.role.Role;
import com.tms.sameasme.model.user.User;
import com.tms.sameasme.repository.role.RoleRepository;
import com.tms.sameasme.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public User findUserById(Long id) {

        return userRepository.findUserById(id);
    }

    @Override
    public List<User> deleteById(Long id) {
        User user = userRepository.findUserById(id);
        user.setActive(false);
      return userRepository.findAll();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean addRoleToUser(Long id, ERole role) {
        User user = userRepository.findUserById(id);
        List<Role> userRoles = user.getRoles();
        boolean flag = true;
        for (Role userRole : userRoles) {
            if(userRole.getName().equals(role)){
                flag=false;
            };
        }
        if(flag){
            userRoles.add(roleRepository.getRoleByName(role));
            user.setRoles(userRoles);
           userRepository.save(user);
           return true;
        }
        return false;
    }

    @Override
    public boolean deleteRoleToUser(Long id, ERole role) {
        User user = userRepository.findUserById(id);
        List<Role> userRoles = user.getRoles();
        boolean flag = false;
        for (Role userRole : userRoles) {
            if(userRole.getName().equals(role)){
                flag=true;
            }
        }
        if(flag){
            userRoles.remove(roleRepository.getRoleByName(role));
            user.setRoles(userRoles);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(UpdateUserDto newUser) {
        User user = userRepository.findUserById(newUser.getId());
        user.setLogin(newUser.getNewLogin());
        user.setName(newUser.getNewName());
        user.setRoles(newUser.getNewRoles().stream().map(role->roleRepository.getRoleByName(ERole.valueOf(role)))
                .collect(Collectors.toList()));
        return userRepository.save(user);
    }
}
