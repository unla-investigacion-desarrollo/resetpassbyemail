package com.ppp.resetpasswordbyemail.services;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import io.jsonwebtoken.Claims;

import com.ppp.resetpasswordbyemail.entities.ApplicationUser;

public interface ITokenService {

    public String createToken(ApplicationUser user, Date exp);
    public Claims createClaims(ApplicationUser user);
    public ResponseEntity<String> refreshToken(Date exp, LocalDateTime dateExpires, String oldToken);

}
