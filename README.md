## MN04

## Micronaut 4.3.8 Documentation

- [User Guide](https://docs.micronaut.io/4.3.8/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.3.8/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.3.8/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)

## Feature test-resources documentation

- [Micronaut Test Resources documentation](https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/)

## Feature jax-rs documentation

- [Micronaut JAX-RS support documentation](https://micronaut-projects.github.io/micronaut-jaxrs/latest/guide/index.html)

## Feature hibernate-jpa documentation

- [Micronaut Hibernate JPA documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#hibernate)

## Feature lombok documentation

- [Micronaut Project Lombok documentation](https://docs.micronaut.io/latest/guide/index.html#lombok)

- [https://projectlombok.org/features/all](https://projectlombok.org/features/all)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

## Feature flyway documentation

- [Micronaut Flyway Database Migration documentation](https://micronaut-projects.github.io/micronaut-flyway/latest/guide/index.html)

- [https://flywaydb.org/](https://flywaydb.org/)


## test this as

./gradlew test

## run this as

./gradlew run

## sample urls

curl http://localhost:8080/charities/list

curl -X POST http://localhost:8080/charities -H 'Content-Type: application/json' -d '
{"name": "American Cancer", "ein": "56-5555", "description": "example"}
'

curl http://localhost:8080/charities/list

curl http://localhost:8080/donors/list

curl -X POST http://localhost:8080/donors -H 'Content-Type: application/json' -d '
{"firstName": "Bob", "lastName": "Smith", "address": "Palo Alto, CA"}
'

curl http://localhost:8080/donors/list

curl -X POST http://localhost:8080/donations -H 'Content-Type: application/json' -d '
{"donor_id": 1, "charity_id": 1, "amount": 2000.0}
'

curl -X DELETE http://localhost:8080/donations/1

curl -X DELETE http://localhost:8080/donors/1

curl -X DELETE http://localhost:8080/charities/1
