package com.tms.sameasme.model.user;

import com.tms.sameasme.model.BaseModel;
import com.tms.sameasme.model.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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

}
