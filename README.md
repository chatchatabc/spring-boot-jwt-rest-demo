Spring REST API with JWT Authentication and MySQL database
=========================================================

# Get started

Run `mvn spring-boot:test-run` to start test App.

# Tech stack

* Java 17
* Spring Boot 3.1
* Spring Security
* MySQL
* Flyway

# Unit Test

* Database Rider: https://github.com/database-rider/database-rider
* DBUnit: https://dbunit.sourceforge.net/
* Spring Boot Test app with Testcontainers and DBUnit support
* DBUnit dataset import from flat xml: please modify `dev.database.url` in pom.xml and run  `mvn dbunit:operation`

# References

* Spring Boot 3 Docs: https://docs.spring.io/spring-boot/docs/current/reference/html/
* frontend-maven-plugin: https://github.com/eirslett/frontend-maven-plugin
* Vite.js: https://vitejs.dev/
