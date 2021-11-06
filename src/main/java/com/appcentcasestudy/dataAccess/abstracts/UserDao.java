package com.appcentcasestudy.dataAccess.abstracts;

import com.appcentcasestudy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
