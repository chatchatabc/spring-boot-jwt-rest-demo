package com.chatchatabc.store.operator.application.config.filter;

import com.chatchatabc.store.operator.application.common.OperatorPrincipal;
import com.chatchatabc.store.operator.application.rest.service.JwtService;
import com.auth0.jwt.interfaces.Payload;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    private final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);

    /**
     * Filter the request and validate the token
     *
     * @param request     the request
     * @param response    the response
     * @param filterChain the filter chain
     * @throws ServletException if an error occurs
     * @throws IOException      if an error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("authorization");
        // continue flow without authorization header, such as static resources, white list urls
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            logRequest(request, response);
            return;
        }

        final String token = authorizationHeader.substring(7); //skip "Bearer "
        final Payload payload = jwtService.validateTokenAndGetPayload(token);

        if (payload == null) {
            filterChain.doFilter(request, response);
            logRequest(request, response);
            return;
        }

        Long operatorId = Long.valueOf(payload.getSubject());
        String operatorName = payload.getClaim("operatorName").asString();
        OperatorPrincipal operatorPrincipal = OperatorPrincipal.of(operatorId, operatorName);

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(payload.getClaim("role").asString()));

        JwtAuthenticationToken authentication = new JwtAuthenticationToken(
                operatorPrincipal,
                null,
                authorities
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue flow with the member in the security context
        filterChain.doFilter(request, response);
        logRequest(request, response, payload.getSubject());
    }

    /**
     * Log the request
     *
     * @param request  the request
     * @param response the response
     */
    private void logRequest(HttpServletRequest request, HttpServletResponse response) {
        // Log path of the request
        log.info("Request path: " + request.getMethod() + " " + request.getRequestURL() + " from " + request.getRemoteAddr() + " with code " + response.getStatus());
    }

    /**
     * Log the request with operator id
     *
     * @param request  the request
     * @param response the response
     */
    private void logRequest(HttpServletRequest request, HttpServletResponse response, String operatorId) {
        // Log path of the request
        log.info("Request path: " + request.getMethod() + " " + request.getRequestURL() + " Operator ID " + operatorId + " from " + request.getRemoteAddr() + " with code " + response.getStatus());
    }
}
