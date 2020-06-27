package com.tms.sameasme.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppUser {
    private Long id;
    private String name;
    private String login;

    public static AppUser fillAppUser(User user){
        AppUser appUser = new AppUser();
        appUser.id=user.getId();
        appUser.name=user.getName();
        appUser.login=user.getLogin();
        return appUser;
    }
}
