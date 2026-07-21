# Gunakan JDK 17 / 21 sesuai versi project
FROM eclipse-temurin:21-jdk-alpine

# Set folder kerja di dalam container
WORKDIR /app

# Copy file jar hasil build ke container
COPY target/*.jar app.jar

# Expose port aplikasi
EXPOSE 8080

LABEL authors="xamrih"

ENTRYPOINT ["java", "-jar", "app.jar"]