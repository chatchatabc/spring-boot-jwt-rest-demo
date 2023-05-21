package com.chatchatabc.store.operator.domain.repository;

import com.chatchatabc.store.operator.domain.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {

    /**
     * Find operator by operator name
     *
     * @param operatorName the operator name
     * @return the operator
     */
    Optional<Operator> findByName(String operatorName);

}
