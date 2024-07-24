package com.tap.service;

import com.tap.util.JwtUtil;

import javax.sql.DataSource;

public class AuthService {
    public String authenticate(String username, String password) {
        // Dummy authentication logic. Replace with real authentication.
        if ("admin".equals(username) && "password".equals(password)) {
            return JwtUtil.generateToken(username);
        }
        return null;
    }
}
