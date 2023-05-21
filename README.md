Spring REST API with JWT Authentication and MySQL database
=========================================================

# Get started

Run `mvn spring-boot:test-run` to start test App.

# Development

* Install Docker desktop from: https://www.docker.com/products/docker-desktop/
* Start Infra Services by `docker compose up -d`
* Run `mvn spring-boot:run` to start App.
* Check MySQL mapping port for 3306 by `docker compose ps`, and modify `dev.database.url` in pom.xml
* Run `mvn dbunit:operation` to import DBUnit dataset
* Open `index.http` in IntelliJ IDEA, and click `getBookById` to test REST API.

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
* Testcontainers: https://www.testcontainers.org/
* frontend-maven-plugin: https://github.com/eirslett/frontend-maven-plugin
* Vite.js: https://vitejs.dev/
