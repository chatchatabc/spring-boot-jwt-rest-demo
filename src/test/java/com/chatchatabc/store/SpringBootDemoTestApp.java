package com.chatchatabc.store;

import org.springframework.boot.SpringApplication;

/**
 * Spring Boot Application with Testcontainers and DBUnit support
 *
 * @author linux_china
 */
public class SpringBootDemoTestApp {

    public static void main(String[] args) {
        System.getProperties().put("spring.docker.compose.enabled", "false");
        SpringApplication.from(BookStoreApplication::main)
                .with(TestContainersConfiguration.class)
                .with(DBUnitConfiguration.class)
                .run(args);
    }

}
