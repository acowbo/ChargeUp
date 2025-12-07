# Stage 1: Build the application
# Use Maven with Eclipse Temurin JDK 8 (Official OpenJDK successor)
FROM maven:3.9-eclipse-temurin-8 AS build

# Set the working directory
WORKDIR /app

# Set Maven memory limit to avoid OOM on systems with limited RAM (e.g., 2GB free)
# 1024m is usually sufficient for this size of project
ENV MAVEN_OPTS="-Xmx1024m"

# Copy the pom.xml and download dependencies
# This step is cached unless pom.xml changes
COPY pom.xml .

# Download dependencies separately to leverage Docker cache
# Using -B for batch mode to reduce log output
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
# Skip tests to save time and memory during build
RUN mvn package -DskipTests -B

# Stage 2: Create the runtime image
# Use Eclipse Temurin JRE 8 (Lightweight and maintained)
FROM eclipse-temurin:8-jre

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
# The wildcard ensures we pick up the jar regardless of version changes
COPY --from=build /app/target/simple-accounting-*.jar app.jar

# Expose the port
EXPOSE 17002

# Run the application
# JAVA_OPTS can be passed at runtime to tune the JVM memory
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
