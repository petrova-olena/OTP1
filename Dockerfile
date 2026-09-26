FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/OTP1_inclass1_assignment_olenape-1.0-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]