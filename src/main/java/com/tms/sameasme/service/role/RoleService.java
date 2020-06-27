package com.tms.sameasme.service.role;

import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.model.role.Role;

public interface RoleService {

    Role getRoleByName(ERole eRole);
}
