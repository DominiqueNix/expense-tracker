FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app 

COPY mnvw .
COPY .mvn .mvn

COPY pom.xml .


RUN ./mvnw dependency:go-offline -build

COPY src src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17.0.1-jdk-slim

ARG DEPENDENCY=/app/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app


ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.expenses.expenses.ExpensesApplication"]
# COPY . .

# RUN mvn clean package -DskipTests

# FROM openjdk:17.0.1-jdk-slim



# COPY --from=build /target/expenses-0.0.1-SNAPSHOT.jar expenses.jar

# EXPOSE 8080

# ENTRYPOINT ["java", "-jar", "expenses.jar"]