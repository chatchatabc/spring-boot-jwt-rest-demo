package com.chatchatabc.store;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.spring.api.DBRider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE, schema = "book_store")
public abstract class BookStoreBaseTest {
}
