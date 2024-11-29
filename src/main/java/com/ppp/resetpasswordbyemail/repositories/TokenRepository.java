package com.ppp.resetpasswordbyemail.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.resetpasswordbyemail.models.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByCode(String code);
}
