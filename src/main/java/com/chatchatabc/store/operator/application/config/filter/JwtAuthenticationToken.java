package com.chatchatabc.store.operator.application.config.filter;

import com.chatchatabc.store.operator.application.common.OperatorPrincipal;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken implements OperatorPrincipal {
    private final Object principal;

    private Object credentials;

    public JwtAuthenticationToken(Object principal,
                                  Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(false);
    }

    public JwtAuthenticationToken(Object principal,
                                  Object credentials,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); //

    }

    @Override
    public Long getId() {
        return this.principal instanceof OperatorPrincipal ?
                ((OperatorPrincipal) this.principal).getId()
                : null;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }


    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
