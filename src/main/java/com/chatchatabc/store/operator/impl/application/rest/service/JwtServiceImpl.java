package com.chatchatabc.store.operator.impl.application.rest.service;

import com.chatchatabc.store.operator.application.rest.service.JwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Payload;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final Long expiration;
    private final Algorithm hmac512;
    private final JWTVerifier verifier;
    /**
     * JWT payload cache because JWT validation and decode still expensive
     */
    private final LoadingCache<String, Payload> jwtPayloadCache = Caffeine.newBuilder()
            .maximumSize(100)
            .build(this::extractJWTPayload);


    public JwtServiceImpl(
            @Value("${server.jwt.secret}")
            String secret,
            @Value("${server.jwt.expiration}")
            Long expiration
    ) {
        this.expiration = expiration;
        hmac512 = Algorithm.HMAC512(secret);
        verifier = JWT.require(hmac512).build();
    }


    /**
     * Generate json web token
     *
     * @param operatorId   the operator id
     * @param operatorName the operator name
     * @param role         the role
     * @return the string
     */
    @Override
    public String generateToken(String operatorId, String operatorName, String role) {
        return JWT.create()
                .withIssuer("BookStore")
                .withSubject(operatorId)
                .withClaim("operatorName", operatorName)
                .withClaim("role", role)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(hmac512);
    }

    /**
     * Validate token and get the jwt payload
     *
     * @param token the token
     * @return the payload
     */
    @Override
    public Payload validateTokenAndGetPayload(String token) {
        return jwtPayloadCache.get(token);
    }

    private Payload extractJWTPayload(String token) {
        try {
            return verifier.verify(token);
        } catch (Exception ignore) {
            return null;
        }
    }
}
