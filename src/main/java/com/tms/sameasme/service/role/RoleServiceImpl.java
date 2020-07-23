package com.tms.sameasme.service.role;

import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.model.role.Role;
import com.tms.sameasme.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(ERole eRole) {
        return roleRepository.getRoleByName(eRole);
    }

}
