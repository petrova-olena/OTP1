FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY pom.xml .
COPY target/OTP1-1.0-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]