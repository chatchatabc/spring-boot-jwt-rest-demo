package com.chatchatabc.store.operator.impl.service;

import com.chatchatabc.store.operator.domain.repository.OperatorRepository;
import com.chatchatabc.store.operator.domain.service.OperatorService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OperatorServiceImpl implements OperatorService {
    private final OperatorRepository operatorRepository;

    public OperatorServiceImpl(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    /**
     * load user by username from user details service
     *
     * @param operatorName operatorName
     * @return UserDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String operatorName) throws UsernameNotFoundException {
        return operatorRepository.findByName(operatorName).map(operator -> new org.springframework.security.core.userdetails.User(
                operator.getUsername(),
                operator.getPassword(),
                operator.isEnabled(),
                operator.isAccountNonExpired(),
                operator.isCredentialsNonExpired(),
                operator.isAccountNonLocked(),
                operator.getAuthorities()
        )).orElseThrow(() -> new UsernameNotFoundException("Operator not found: " + operatorName));
    }
}
