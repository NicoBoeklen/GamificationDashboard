# Spring Boot RESTful API

> This is an example of a Java RESTful API with Spring Boot and H2 Databank.

## Prerequisites

- [Install a JDK](https://www.oracle.com/de/java/technologies/downloads/) in at least version 21.
- Make sure that `JAVA_HOME` is set correctly to the root directory of your JDK. You can check with this command: `echo %JAVA_HOME%` (or `echo $JAVA_HOME` on Linux / Git Bash) (or `echo $Env:JAVA_HOME` in Powershell)
- Make sure that the JDK `bin` folder is added to your `PATH`. You can check with this command: `echo %PATH%` (or `echo $PATH` on Linux / Git Bash) (or `echo $Env:PATH` in Powershell)
- You can also install [Maven](https://maven.apache.org/) yourself, but if you use the Maven Wrapper scripts (`mvnw` or `mvnw.cmd`), this is not necessary, since Maven will be downloaded in this case


## Usage

```bash
# build and package executable --> appears in target/rest-api.jar
./mvnw clean install

# execute tests only
./mvnw test

# generate test coverage report (execute tests first) --> appears in target/site/jacoco/index.html
./mvnw jacoco:report

# build and package executable without running tests
./mvnw clean install -DskipTests

# run the created JAR file
java -jar ./target/rest-api.jar

# for development: build and run in live-reload mode (rebuild on save)
./mvnw spring-boot:run
```

## Documentation

- General references: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle
- Default.Application properties: https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html
