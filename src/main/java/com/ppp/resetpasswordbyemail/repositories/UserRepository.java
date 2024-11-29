package com.ppp.resetpasswordbyemail.repositories;

import java.util.Optional;

//import org.h2.engine.UserAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.resetpasswordbyemail.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}