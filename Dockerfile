# Multi-stage build: build the Spring Boot jar, then run it.

# -------- Build stage --------
FROM gradle:9.3.0-jdk17 AS build

WORKDIR /home/gradle/project

# Copy build scripts first (better layer caching).
COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts ./
COPY --chown=gradle:gradle gradle ./gradle

# Copy source.
COPY --chown=gradle:gradle src ./src

# Build an executable Spring Boot jar.
RUN gradle bootJar --no-daemon

# -------- Run stage --------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built jar from the build stage.
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]