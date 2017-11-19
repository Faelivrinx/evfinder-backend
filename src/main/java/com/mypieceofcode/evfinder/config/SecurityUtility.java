package com.mypieceofcode.evfinder.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class SecurityUtility {
    private static final String SALT = "43jhkkfdhas2fds4fdsa32";


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Bean
    public String randomPassword(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXZ123456789";
        StringBuilder salt = new StringBuilder();

        Random rnd = new Random();

        while (salt.length() < 18){
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        return salt.toString();
    }
}
