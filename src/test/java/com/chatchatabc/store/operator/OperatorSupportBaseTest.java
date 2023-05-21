package com.chatchatabc.store.operator;

import com.chatchatabc.store.BookStoreBaseTest;
import com.chatchatabc.store.operator.domain.model.Operator;
import com.chatchatabc.store.operator.domain.repository.OperatorRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

@DataSet("db/datasets/operator.xml")
public class OperatorSupportBaseTest extends BookStoreBaseTest {
    @Autowired
    protected OperatorRepository operatorRepository;

    @Nullable
    public Operator getOperator(Long id) {
        return operatorRepository.findById(id).orElse(null);
    }
}
