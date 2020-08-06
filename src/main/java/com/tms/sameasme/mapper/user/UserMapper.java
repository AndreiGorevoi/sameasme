package com.tms.sameasme.mapper.user;

import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.model.role.Role;
import com.tms.sameasme.model.user.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserMapper {
    private Long id;
    private String login;
    private String name;
    private List<Role> roles;
    private List<Post> posts;

    public UserMapper(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.name = user.getName();
        this.roles = user.getRoles();
        this.posts = user.getPosts();
    }

    public static List<UserMapper> getAllUsers(List<User> userList){
        return userList.stream().map((user -> new UserMapper(user))).collect(Collectors.toList());
    }

    public static UserMapper getUser(User user){
        return new UserMapper(user);
    }
}
