package com.zulvit.userDatabaseSpring.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {
    public String encode(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        return bCryptPasswordEncoder.encode(password);
    }
}
