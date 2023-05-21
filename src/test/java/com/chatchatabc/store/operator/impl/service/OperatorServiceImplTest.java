package com.chatchatabc.store.operator.impl.service;

import com.chatchatabc.store.BookStoreBaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class OperatorServiceImplTest extends BookStoreBaseTest {
    @Autowired
    private OperatorServiceImpl operatorService;

    @Test
    public void testLoadUserByUsername() {
        assertThat(operatorService.loadUserByUsername("admin")).isNotNull();
        assertThatThrownBy(() -> operatorService.loadUserByUsername("admin2"));
    }
}