package com.chatchatabc.store.operator.application.rest;

import com.chatchatabc.store.operator.application.rest.service.JwtService;
import com.chatchatabc.store.operator.domain.model.Operator;
import com.chatchatabc.store.operator.domain.repository.OperatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class OperatorController {
    private static final Logger log = LoggerFactory.getLogger(OperatorController.class);
    private final OperatorRepository operatorRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public OperatorController(OperatorRepository operatorRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.operatorRepository = operatorRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public record LoginForm(String username, String password) {
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        try {
            Optional<Operator> operator = operatorRepository.findByName(loginForm.username());
            if (operator.isEmpty()) {
                throw new Exception("Operator not found");
            }

            // Authenticate User
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginForm.username(),
                            loginForm.password()
                    )
            );

            // Generate JWT
            String token = jwtService.generateToken(
                    operator.get().getId().toString(),
                    operator.get().getName(),
                    operator.get().getRole()
            );
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Access-Token", token);

            return ResponseEntity.ok().headers(headers).body(operator.get());

        } catch (Exception e) {
            log.error("Login failed for " + loginForm.username, e);
            return ResponseEntity.badRequest().build();
        }
    }
}
