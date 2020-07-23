package com.tms.sameasme.dto.user;

import com.tms.sameasme.model.role.Role;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUserDto {
    private Long id;
    private String newName;
    private String newLogin;
    private List<String> newRoles;

}
