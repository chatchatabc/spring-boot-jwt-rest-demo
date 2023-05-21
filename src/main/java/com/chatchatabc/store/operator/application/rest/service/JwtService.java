package com.chatchatabc.store.operator.application.rest.service;

import com.auth0.jwt.interfaces.Payload;

public interface JwtService {

    /**
     * Generate json web token
     *
     * @param operatorId   the operator id
     * @param operatorName the operator name
     * @param role         the role
     * @return the string
     */
    String generateToken(String operatorId, String operatorName, String role);

    /**
     * Validate token and get jwt payload
     *
     * @param token the token
     * @return the payload
     */
    Payload validateTokenAndGetPayload(String token);
}
