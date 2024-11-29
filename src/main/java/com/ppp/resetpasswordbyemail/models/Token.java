package com.ppp.resetpasswordbyemail.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idToken;

    @Column(length = 100, nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "idUser")
    private User user;

     public long getIdToken() {
        return idToken;
    }

    public void setIdToken(long idToken) {
        this.idToken = idToken;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Token() {
    }

    public Token(long idToken, String code, LocalDateTime expirationDate) {
        this.idToken = idToken;
        this.code = code;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Token [idToken=" + idToken + ", code=" + code + ", expirationDate=" + expirationDate + "]\n";
    }

}
