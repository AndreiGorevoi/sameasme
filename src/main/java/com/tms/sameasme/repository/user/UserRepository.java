package com.tms.sameasme.repository.user;

import com.tms.sameasme.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("from User u where u.login=?1")
    User findUserByLogin(String login);

    @Query("from User u where u.id=?1")
    User findUserById(Long id);
}
