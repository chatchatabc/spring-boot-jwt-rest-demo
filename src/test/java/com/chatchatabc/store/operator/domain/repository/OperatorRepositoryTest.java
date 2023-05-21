package com.chatchatabc.store.operator.domain.repository;

import com.chatchatabc.store.operator.OperatorSupportBaseTest;
import com.chatchatabc.store.operator.domain.model.Operator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OperatorRepositoryTest extends OperatorSupportBaseTest {

    @Test
    void testFindById() {
        assertThat(operatorRepository.findById(1L)).isPresent();
    }

    @Test
    void testFindByOperatorName() {
        Optional<Operator> operator = operatorRepository.findByName("anthony");
        assertThat(operator).isPresent();
        assertThat(operator.get().getId()).isEqualTo(1L);
    }

}