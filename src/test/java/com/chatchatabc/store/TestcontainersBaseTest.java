package com.chatchatabc.store;

import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Spring Boot base test case with TestContainers support
 *
 * @author linux_china
 */
@Import(TestContainersConfiguration.class)
@Testcontainers
public abstract class TestcontainersBaseTest extends BookStoreBaseTest {

}
