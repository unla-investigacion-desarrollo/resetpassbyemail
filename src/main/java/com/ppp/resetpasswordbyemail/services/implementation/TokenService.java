package com.ppp.resetpasswordbyemail.services.implementation;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.ppp.resetpasswordbyemail.constants.SecurityConstants;
import com.ppp.resetpasswordbyemail.entities.ApplicationUser;
import com.ppp.resetpasswordbyemail.entities.Session;
import com.ppp.resetpasswordbyemail.helpers.MessageHelper.Message;
import com.ppp.resetpasswordbyemail.models.auth.LoginResponse;
import com.ppp.resetpasswordbyemail.models.auth.ProfileDTO;
import com.ppp.resetpasswordbyemail.repositories.ApplicationUserRepository;
import com.ppp.resetpasswordbyemail.repositories.SessionRepository;
import com.ppp.resetpasswordbyemail.services.ITokenService;

public class TokenService implements ITokenService{

     @Autowired
    @Qualifier("applicationUserRepository")
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    @Qualifier("sessionRepository")
    private SessionRepository sessionRepository;

    public Claims createClaims(ApplicationUser user) {

        Map<String, Object> customClaims = new HashMap<String, Object>();
        customClaims.put(SecurityConstants.CustomSecurityClaims.ROLE, user.getRole().getCode());
        customClaims.put(SecurityConstants.CustomSecurityClaims.EMAIL, user.getEmail());
        customClaims.put(SecurityConstants.CustomSecurityClaims.USERNAME, user.getUsername());

        return Jwts.claims(customClaims);
    }

    public String createToken(ApplicationUser user, Date exp) {

        Key key = Keys.hmacShaKeyFor(SecurityConstants.SECRET.getBytes());

        return Jwts.builder()
                .setClaims(createClaims(user))
                .setSubject(Long.toString(user.getUserId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public ResponseEntity<String> refreshToken(Date exp, LocalDateTime dateExpires, String oldToken) {

        try {
            Optional<Session> session = sessionRepository.findByToken(oldToken);

            if (!session.isPresent()) {
                return Message.ErrorSearchEntity();
            }

            ApplicationUser user = session.get().getUser();

            if (user == null) {
                return Message.ErrorSearchEntity();
            }

            String token = createToken(user, exp);

            if (session.isPresent()) {
                session.get().setToken(token);
                session.get().setIssuedAt(LocalDateTime.now());
                session.get().setExpiresAt(dateExpires);

                sessionRepository.save(session.get());
            }

            ProfileDTO profile = ProfileDTO.builder().username(user.getUsername()).name(user.getName())
                    .surname(user.getSurname()).roleCode(user.getRole().getCode()).build();

            LoginResponse response = new LoginResponse(token, profile, dateExpires.toString());

            return Message.Ok(response);

        } catch (Exception e) {
            return Message.ErrorException(e);
        }
    }

}
