package com.coding.assignment.bankservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class GlobalConfiguration {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        int strength = 10; // work factor of bcrypt
        return new BCryptPasswordEncoder(strength, new SecureRandom());
    }

}
