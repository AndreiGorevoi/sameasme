package com.tms.sameasme.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tms.sameasme.model.BaseModel;
import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.model.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseModel {

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
