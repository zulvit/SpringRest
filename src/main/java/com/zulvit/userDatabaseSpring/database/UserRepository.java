package com.zulvit.userDatabaseSpring.database;

import com.zulvit.userDatabaseSpring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
