package com.ppp.resetpasswordbyemail.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ppp.resetpasswordbyemail.models.Token;
import com.ppp.resetpasswordbyemail.repositories.TokenRepository;
import com.ppp.resetpasswordbyemail.repositories.UserRepository;

@Service
public class TokenService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    
}
