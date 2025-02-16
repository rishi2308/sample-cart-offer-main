# Setup Solution

## Setting Up Docker Server
1. Installed Docker.
2. Modified Initializer JSON to add mocks.
3. Ran `docker compose up`.

## Setting Up Spring Boot Server
1. Installed JDK 11.
2. Added getter and setter methods wherever required.
3. Changed default port to mock port in test cases.
4. Added new test cases.
5. Built snapshot jar using Maven:
    ```sh
    ./mvnw clean install -DskipTests
    ```
6. Ran Spring Boot server:
    ```sh
    java -jar target/simple-springboot-app-0.0.1-SNAPSHOT.jar
    ```

## Running Tests
1. Ran test cases using:
    ```sh
    ./mvnw test
    ```