package com.chatchatabc.store.operator.impl.application.rest.service;

import com.chatchatabc.store.BookStoreBaseTest;
import com.auth0.jwt.interfaces.Payload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtServiceImplTest extends BookStoreBaseTest {

    @Autowired
    private JwtServiceImpl jwtService;

    @Test
    public void testGenerateToken() {
        final String token = jwtService.generateToken("1", "anthony", "ROLE_ADMIN");
        assertThat(jwtService.validateTokenAndGetPayload(token + "1")).isNull();
        final Payload payload = jwtService.validateTokenAndGetPayload(token);
        assertThat(payload.getClaim("operatorName").asString()).isEqualTo("anthony");
        assertThat(payload.getClaim("role").asString()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    public void testJWTDecodeCache() {
        final String token = jwtService.generateToken("1", "anthony", "ROLE_ADMIN");
        final Payload payload = jwtService.validateTokenAndGetPayload(token);
        final Payload payload2 = jwtService.validateTokenAndGetPayload(token);
        assert payload2 == payload;
    }
}
