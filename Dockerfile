FROM eclipse-temurin:17-jdk-focal

COPY target/*.jar app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]