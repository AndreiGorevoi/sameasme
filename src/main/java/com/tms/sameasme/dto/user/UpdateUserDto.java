package com.tms.sameasme.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UpdateUserDto {
    private Long id;
    private String newName;
    private String newLogin;
    private List<String> newRoles;

}
