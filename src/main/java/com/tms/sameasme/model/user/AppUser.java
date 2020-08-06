package com.tms.sameasme.model.user;

import com.tms.sameasme.model.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AppUser {
    private Long id;
    private String name;
    private String login;
    private List<Role> roleList;

    public static AppUser fillAppUser(User user){
        AppUser appUser = new AppUser();
        appUser.id=user.getId();
        appUser.name=user.getName();
        appUser.login=user.getLogin();
        appUser.roleList=user.getRoles();
        return appUser;
    }
}
