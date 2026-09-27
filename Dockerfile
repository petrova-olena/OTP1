FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY pom.xml .
COPY src ./src

CMD ["java", "-jar", "target/OTP1-1.0-SNAPSHOT.jar"]