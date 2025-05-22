package com.ppp.resetpasswordbyemail.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.resetpasswordbyemail.entities.Session;

@Repository("sessionRepository")
public interface SessionRepository extends JpaRepository<Session, Long> {

    public abstract Optional<Session> findByToken(String token);

}
