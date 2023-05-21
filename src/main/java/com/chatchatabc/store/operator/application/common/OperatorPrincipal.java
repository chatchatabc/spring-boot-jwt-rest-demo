package com.chatchatabc.store.operator.application.common;

import java.security.Principal;

/**
 * Operator principal after authentication
 */
public interface OperatorPrincipal extends Principal {

    Long getId();


    public static OperatorPrincipal of(Long id, String name) {
        return new OperatorPrincipal() {
            @Override
            public Long getId() {
                return id;
            }

            @Override
            public String getName() {
                return name;
            }
        };
    }
}
