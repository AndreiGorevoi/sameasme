package com.tms.sameasme.repository.role;

import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("from Role r where r.name=?1")
    Role getRoleByName(ERole erole);
}
