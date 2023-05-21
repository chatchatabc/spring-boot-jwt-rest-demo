package com.chatchatabc.store.operator.impl.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final Logger log = LoggerFactory.getLogger(PasswordEncoderTest.class);

    @Test
    void testEncodePassword() {
        String password = "123456";
        String hashPassword = passwordEncoder.encode("123456");
        log.info("hashPassword:{}", hashPassword);

        assertThat(passwordEncoder.matches(password, hashPassword)).isTrue();
    }

}
